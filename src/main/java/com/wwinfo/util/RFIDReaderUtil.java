package com.wwinfo.util;

import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;

import cn.hutool.core.collection.CollUtil;
import com.wwinfo.model.*;
import com.wwinfo.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
 * RFID设备数据读取
 */
@Slf4j
@Component
public class RFIDReaderUtil {

	public static final int DIRECTORY_IN = 0;
	public static final int DIRECTORY_OUT = 1;
	private static int portListen = 19902;
	private static List<HashMap> readerList = new ArrayList<HashMap>();
	private static int rfidSameTime = 3;	//同一卡号视为一次的间隔时间
	private static int rfidSplit = 60;		//RFID进出空闲时间，即这段时间内没有再读取到后进行进出分析
	private static int rfidmanmatchtime = 30;	//资产进出与人员匹配间隔

	@Autowired
	private TConfigService configService;

	@Autowired
	private RfidrecordService rfidrecordService;

	@Autowired
	private RfidreaderService rfidreaderService;

	@Autowired
	private AlarmService alarmService;

	@Autowired
	private BusinUtil businUtil;


	public void init(){
		TConfig config = configService.getConfig("rfidsametime");
		if(config != null){
			rfidSameTime = Integer.valueOf(config.getValue());
			if (rfidSameTime < 1)
				rfidSameTime = 3;
		}

		config = configService.getConfig("rfidsplit");
		if(config != null){
			rfidSplit = Integer.valueOf(config.getValue());
			if (rfidSplit < 3)
				rfidSplit = 60;
		}
		config = configService.getConfig("rfidmanmatchtime");
		if(config != null){
			rfidmanmatchtime = Integer.valueOf(config.getValue());
			if (rfidmanmatchtime < 5)
				rfidmanmatchtime = 30;
		}
		config = configService.getConfig("rfidlistenport");
		if(config != null){
			portListen = Integer.valueOf(config.getValue());
			if (portListen < 1)
				portListen = 10000;
		}
		flushReaderList();
		//创建监听线程
		(new RFIDReaderUtil()).start();
	}

	public void start(){
		ListenRun listen = new ListenRun();
		new Thread(listen,"RFIDListen").start();
	}

	/*
	 * 组建向RFID设备发送的字节内容
	 */
	public byte[] buildSendData(int interfaceType,Byte addr){
		if (interfaceType == 1){		//原708设备
			byte[] sendData={0x0A,0x01,0x02,(byte)0x9A,0x00};
			if (null != addr)
				sendData[1]=addr;
			byte sum = 0;
			for(int i=0;i<sendData.length;i++){
				sum += sendData[i];
			}
			sum = (byte)~sum;
			sum ++;
			sendData[4]=sum;
			return sendData;
		} else{							//ML-M5000
			byte[] sendData={0x01,0x04,0x01,0x03};
			if (null != addr)
				sendData[0]=addr;
			return sendData;
		}
	}

	/*
     * Read到请求数据的回调
     */
    private class ReadHandler implements CompletionHandler<Integer, AsynchronousSocketChannel> {
    	public HashMap readerInfo;		//读卡器信息
        private ByteBuffer buffer;		//读取缓冲内存
        private int interfaceType = 0;	//读卡器类型

        public ReadHandler(HashMap reader,ByteBuffer buffer) {
        	this.readerInfo = reader;
            this.buffer = buffer;
			Object temp = reader.get("interfaceType");
			if (null != temp){
				try{
					interfaceType = Integer.valueOf(temp.toString());
				}catch(Exception e){
					e.printStackTrace();
					log.error("ReadHandler中interfaceType转化异常: {}", e);
				}
			}
        }

        @Override
        public void completed(Integer result, AsynchronousSocketChannel attachment) {
            try {
				log.info("RFIDReaderUtil completed()中的result: {}", result);
                if (result < 0) {	// 客户端关闭了连接
                	attachment.close();
                	readerInfo.put("socketChannel",null);
        			log.error("client closed. ip={}", attachment.getRemoteAddress().toString());
        			return;
                } else if (result == 0) {	// 空数据
                } else {
                	try{
	                	if (interfaceType == 1){
	                		readData1(attachment);
	                	}else if (interfaceType == 0){
	                		readData0(attachment);
	                	}
                    } catch (Exception e) {
						log.error("RFIDReaderUtil completed()异常: {}", e);
                	}
                }
                attachment.read(buffer, attachment, this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void failed(Throwable exc, AsynchronousSocketChannel attachment) {
            exc.printStackTrace();
            try{
            	attachment.close();
            	readerInfo.put("socketChannel",null);
    			log.error("client failed. ip=: {}", attachment.getRemoteAddress().toString());
            }catch(Exception e){
				log.error("RFIDReaderUtil failed()异常: {}", e);
            }
        }

        /*
         * 处理ML-M5000五字节协议返回的内容
         */
        private void readData0(AsynchronousSocketChannel attachment) throws Exception{
        	if (buffer.position()<6){
        		//还没有6个字符，等待下次
        		return;
        	}
        	int pos = buffer.position();	//保存当前已获取数据的位置
            //开始从头读取
            buffer.flip();
            byte[] head=new byte[4];
            buffer.get(head);
    		if (0xFF != (head[0]&0xFF) || 0xFF != (head[1]&0xFF)){
    			log.error("read first two bytes data are not 0xFF from RFID,ip={}", attachment.getRemoteAddress().toString());
    			attachment.shutdownInput();
    			attachment.shutdownOutput();
    			return;
    		}
    		//检查是否读取到EEEE结束标记
    		int dataLen = buffer.remaining();		//剩余的数据长度
    		byte[] cardData=new byte[dataLen];		//保存卡号数据，每5个字节为一个卡号
    		int cardDataLen = 0;					//卡号数据的实际长度
    		boolean hasEnd=false;					//是否有结束标记
    		byte b1 = buffer.get();
			byte b2 = 0;
			int i = 1;
    		do{
    			b2 = buffer.get();
    			if (0xEE == (b1&0xFF) && 0xEE == (b2&0xFF)){
    				hasEnd = true;
    				break;
    			}
    			cardData[cardDataLen] = b1;
    			cardDataLen ++;
    			b1 = b2;
    			i++;
    		}while(i<dataLen);
    		if (!hasEnd){
    			//没有结束标记，等下次处理
    			buffer.clear();	//不会清除数据
    			buffer.position(pos);
    			return;
    		}
    		buffer.compact();		//剩余字节移到最前面，供下次处理
    		if (cardDataLen < 1){
    			//没有卡号
    			return;
    		}
    		if (cardDataLen%5 != 0){
    			//卡号字节数不是5的倍数
    			log.error("card data len is not multiple of 5,ip={}", attachment.getRemoteAddress().toString());
    			return;
    		}
    		int number = cardDataLen / 5;
    		for(i=0;i<number;i++){
    			byte cardType = cardData[i*5];
    			boolean isLowerPower = ((cardType & 0x80) == 0x80);
    			cardType &= 0x7F;
    			String cardNo = "";
    			if (cardType == 0x05){
    				//双频卡
    				byte[] btTemp = new byte[4];
    				btTemp[0]=btTemp[1]=0;
    				btTemp[2]=cardData[i*4+3];
    				btTemp[3]=cardData[i*4+4];
    				cardNo = Integer.toString(ByteBuffer.wrap(btTemp).getShort()&0xFFFF);
    			}else if (cardType == 0x01){
    				//单频卡
    				byte[] btTemp=new byte[4];
    				for(int j=0;j<4;j++)
    					btTemp[j]=cardData[i*5+1+j];
    				cardNo = Long.toString(ByteBuffer.wrap(btTemp).getInt()&0x0FFFFFFFFL);
    			}
    			log.debug("read card no: {},  from: {}", cardNo, attachment.getRemoteAddress().toString());
    			settleReadCard(cardNo,isLowerPower,readerInfo);
    		}
        }

        /*
         * 处理原708设备的协议返回内容
         */
        private void readData1(AsynchronousSocketChannel attachment)
        	throws Exception{
        	if (buffer.position()<3){
        		//还没有3个字符，等待下次
        		return;
        	}
        	int pos = buffer.position();	//保存当前已获取数据的位置
            //开始从头读取
            buffer.flip();
            byte[] head=new byte[3];
            buffer.get(head);
    		if (0x0B != head[0]){
    			log.error("read first byte data is not 0x0B from RFID,ip={}", attachment.getRemoteAddress().toString());
    			attachment.shutdownInput();
    			attachment.shutdownOutput();
    			return;
    		}
    		if (head[2] < 3 || ((head[2] - 3) % 8) != 0 ){
    			log.error("data length is wrong from RFID,ip={}", attachment.getRemoteAddress().toString());
    			attachment.shutdownInput();
    			attachment.shutdownOutput();
    			return;
    		}
    		int number = (head[2] - 3) / 8;	//读到的RFID个数
    		if (buffer.remaining()<head[2]){
    			//数据还不够，等下次处理
    			buffer.clear();	//不会清除数据
    			buffer.position(pos);
    			return;
    		}
    		buffer.position(buffer.position() + 2);	//移动两个字节
    		for(int i=0;i<number;i++){
    			byte[] cardInfo = new byte[8];
    			buffer.get(cardInfo);		//每个卡信息为8个字节
    			//把字节数转换卡号和低电信息
				byte[] btTemp=new byte[4];
				for(int j=0;j<4;j++)
					btTemp[j]=cardInfo[j];
				String cardNo = Long.toString(ByteBuffer.wrap(btTemp).getInt()&0x0FFFFFFFFL);
    	    	//第5字节表示是否低电压
    	    	boolean isLowerPower = (cardInfo[4]!=0);

    			settleReadCard(cardNo,isLowerPower,readerInfo);
    		}
    		buffer.compact();		//剩余字节移到最前面，供下次处理
        }
    }

   /*
     * 无源RFID异步读取
     * @params:
     * 	reader: 设备信息
     * 	antennaNum: 天线个数
     */
	private void asyncReadPassive(HashMap reader,int antennaNum){
		ZKYAsynReader asynReader = new ZKYAsynReader();
		if (!asynReader.init(reader,(String)reader.get("readerIP"),antennaNum)){
			return;
		}
		reader.put("asynReader",asynReader);
		if (!asynReader.StartReading()){
			return;
		}
	}

    /*
     * 从数据库中读取所有RFID探测器
     */
    public synchronized void flushReaderList(){
		log.info("flushReaderList()执行");
		//对于中科院无源RFID设备，先关闭正在运行的读取线程
		stopZKYRead();
		//读取所有RFID探测器
		List<HashMap<String,Object>> listTemp = rfidreaderService.getAllReader();
		log.info("所有RFID探测器listTemp: {}", listTemp.toString());
		if (null == listTemp)
			return;
		synchronized(readerList){
			//从列表中清除无源RFID设备
			Iterator<HashMap> iterator = readerList.iterator();
			while (iterator.hasNext()) {
				HashMap<String,Object> item = iterator.next();
				int interfaceType = -1;
				try{
					Object temp = item.get("interfaceType");
					if (null != temp){
						try{
							interfaceType = Integer.valueOf(temp.toString());
						}catch(Exception e){
							e.printStackTrace();
						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				if (interfaceType >= 2 && interfaceType<=5){	//无源1,2,3,4天线
					iterator.remove();
				}
			}

			for(HashMap<String,Object> item: listTemp){
				String name = (String)item.get("readerName");
				boolean exists = false;
				HashMap existReader = null;
				for(HashMap reader: readerList){
					if (name.equals(reader.get("readerName"))){
						exists = true;
						existReader= reader;
						break;
					}
				}
				if (!exists){
					readerList.add(item);
				}else{
					existReader.put("readerIP", item.get("readerIP"));
					existReader.put("interfaceType", item.get("interfaceType"));
				}
			}
		}
		//对于中科院无源RFID设备，每个设备开启线程读取
		startZKYRead();
		log.info("flushReaderList complete");
	}

	//对于中科院无源RFID设备，先关闭正在运行的读取线程
	private void stopZKYRead(){
		synchronized(readerList){
			for(HashMap<String,Object> reader: readerList){
				int interfaceType = -1;
				try{
					Object temp = reader.get("interfaceType");
					if (null != temp){
						try{
							interfaceType = Integer.valueOf(temp.toString());
						}catch(Exception e){
							e.printStackTrace();
						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				if (interfaceType >= 2 && interfaceType<=5){	//无源1,2,3,4天线
					ZKYAsynReader asynReader = (ZKYAsynReader)reader.get("asynReader");
					if (null != asynReader){
						asynReader.StopReading();
						reader.put("asynReader",null);
					}
				}
			}
		}
	}
	//对于中科院无源RFID设备，每个设备开启线程读取
	private void startZKYRead(){
		//为每个设备启动读取线程
		synchronized(readerList){
			for(HashMap<String,Object> reader: readerList){
				int interfaceType = -1;
				try{
					Object temp = reader.get("interfaceType");
					if (null != temp){
						try{
							interfaceType = Integer.valueOf(temp.toString());
						}catch(Exception e){
							e.printStackTrace();
						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				if (interfaceType >= 2 && interfaceType<=5){	//无源1,2,3,4天线
					//开启异步读取方式，即线程读取
					log.info("RFIDReaderUtil中开启异步读取方式，即线程读取");
					asyncReadPassive(reader,interfaceType - 1);
				}
			}
		}
	}

    /*
     * 根据IP地址匹配对应的RFID探测器
     */
    private HashMap matchedReader(String ip){
    	HashMap matchedReader = null;
		synchronized(readerList){
			for(HashMap item: readerList){
				if (ip.equals(item.get("readerIP"))){
					matchedReader = item;
					break;
				}
			}
		}
    	return matchedReader;
    }

	private class ListenRun implements Runnable {
		@Override
		public void run(){
			log.info("ListenRun run ");
			try{
				AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(portListen));
				while (true){
					log.info("ListenRun while ");
		            Future<AsynchronousSocketChannel> future = serverChannel.accept();
		            AsynchronousSocketChannel client = null;
		            try {
		            	client = future.get();
		            	//String clientIP = client.getRemoteAddress().toString();
		            	InetSocketAddress addr = (InetSocketAddress)client.getRemoteAddress();
		            	String clientIP = addr.getAddress().toString();
		            	if (clientIP.startsWith("/"))
		            		clientIP = clientIP.substring(1);
		            	log.debug("RFID地址：{}", clientIP);
		            	if (clientIP.isEmpty())
		            		continue;
		            	//tcp各项参数
		                client.setOption(StandardSocketOptions.TCP_NODELAY, true);
		                client.setOption(StandardSocketOptions.SO_KEEPALIVE, true);
		                client.setOption(StandardSocketOptions.SO_SNDBUF, 64);
		                client.setOption(StandardSocketOptions.SO_RCVBUF, 512);

		                if (client.isOpen()) {
		                	//根据IP地址匹配对应的RFID探测器
		                	HashMap matchedReader = matchedReader(clientIP);
		                	//if (null == matchedReader){
		                	//	//没匹配到，从数据库中加载所有的RFID设备信息
		                	//	flushReaderList();
		                	//	matchedReader = matchedReader(clientIP);
		                	//}
		                	if (null == matchedReader){
				            	log.debug("未匹配到RFID设备，客户端IP地址：{}", clientIP);
				            	try{
				            		client.close();
				            	}catch(Exception e){
				            		e.printStackTrace();
				            	}
				            	continue;
		                	}

		                	matchedReader.put("socketChannel", client);

		                    ByteBuffer buffer = ByteBuffer.allocate(512);
		                    buffer.clear();
		                    client.read(buffer, client, new ReadHandler(matchedReader,buffer));
		                } else {
							log.info("client.isOpen: {}", client.isOpen());
						}
		            }catch (Exception e){
		                e.printStackTrace();
		            }
		        }
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

    /*
     * 处理读到的卡号内容
     * @param:
     * 		cardInfo: 读取到的8字节信息
     * 		readerInfo: 对应的RFID探测器信息
     */
    public void settleReadCard(String cardNo, boolean isLowerPower,HashMap readerInfo){
    	if (null == cardNo || cardNo.isEmpty())
    		return;
    	//检查是否连续读到同一卡号
    	boolean isAlready = false;
    	HashMap<String,Long> readedCard = (HashMap<String,Long>)readerInfo.get("readedCard");
    	if (null != readedCard){
    		synchronized(readedCard){
    			Long lastTime = readedCard.get(cardNo);
    			if (null != lastTime){
    				if ((new Date()).getTime() - lastTime < rfidSameTime*1000L){
    					//连续读到卡，不处理
    					isAlready = true;
    				}
    			}
				readedCard.put(cardNo, (new Date()).getTime());
    		}
    	}else{
    		readedCard =new HashMap<String,Long>();
			readedCard.put(cardNo, (new Date()).getTime());
			readerInfo.put("readedCard", readedCard);
    	}
    	if (isAlready)
    		return;
       	//保存读到卡号事件到数据库
		log.info("RFIDReaderUtil中的settleReadCard方法开始保存读到卡号事件到数据库");
		Rfidrecord rfidrecord = new Rfidrecord();
        List<Rfidrecord> listTemp = rfidrecordService.getByrFidNo(cardNo);
        if(CollUtil.isNotEmpty(listTemp)){
			rfidrecord.setIsOther(0);
			rfidrecord.setIsAsset(1);
		} else {
			rfidrecord.setIsOther(1);
			rfidrecord.setIsAsset(0);
		}
		Rfidreader rfidreader = rfidreaderService.getByReaderName(readerInfo.get("readerName").toString());
		rfidrecord.setReaderID(rfidreader.getID());
		rfidrecord.setRfidNo(cardNo);
		rfidrecord.setStatus(0);
		rfidrecordService.add(rfidrecord);

    	//低电报警
    	if (isLowerPower){
			log.info("卡号: {}低电报警", cardNo);
    		//Alarm.addAlarm("卡号为"+cardNo+"的RFID低电", 1, "低电报警", cardNo, null);
			Alarm alarm = new Alarm();
			alarm.setContent("卡号为"+cardNo+"的RFID低电");
			alarm.setAlarmType(1);
			alarm.setSourceID(Long.valueOf(cardNo));
			alarm.setAlarmEmail(0);
			alarm.setIsSend(0);
			alarmService.add(alarm);

			businUtil.sendMail("低电报警", "卡号为"+cardNo+"的RFID低电");
		}
    }

	public List<HashMap> getReaderList(){
		return RFIDReaderUtil.readerList;
	}
 }