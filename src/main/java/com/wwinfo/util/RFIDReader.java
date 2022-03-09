package com.wwinfo.util;

import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;

//import org.apache.log4j.Logger;

import com.uhf.api.cls.Reader;
import com.uhf.api.cls.Reader.AntPortsVSWR;
import com.uhf.api.cls.Reader.ConnAnts_ST;
import com.uhf.api.cls.Reader.Mtr_Param;
import com.uhf.api.cls.Reader.READER_ERR;
import com.uhf.api.cls.Reader.Region_Conf;
import com.uhf.api.cls.Reader.TAGINFO;
import com.wwinfo.model.Alarm;

/*
 * RFID设备数据读取
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class RFIDReader {
	/*public static final int DIRECTORY_IN = 0;
	public static final int DIRECTORY_OUT = 1;
	private static Logger logger = Logger.getLogger(RFIDReader.class.getName());
	private static DBBusiness db = new DBBusiness();
	private static int portListen = 10000;
	private static List<HashMap> readerList = new ArrayList<HashMap>();
	private static int rfidSameTime = 3;	//同一卡号视为一次的间隔时间
	private static int rfidSplit = 60;		//RFID进出空闲时间，即这段时间内没有再读取到后进行进出分析
	private static int rfidmanmatchtime = 30;	//资产进出与人员匹配间隔
	
	public static void init(){
		DBSystem dbSys = new DBSystem();
		List<HashMap<String,Object>> listCfg = dbSys.getConfig("rfidsametime");
		if (null != listCfg && !listCfg.isEmpty()){
			rfidSameTime = Integer.valueOf(listCfg.get(0).get("intValue").toString());
			if (rfidSameTime < 1)
				rfidSameTime = 3;
		}
		listCfg = dbSys.getConfig("rfidsplit");
		if (null != listCfg && !listCfg.isEmpty()){
			rfidSplit = Integer.valueOf(listCfg.get(0).get("intValue").toString());
			if (rfidSplit < 3)
				rfidSplit = 60;
		}
		listCfg = dbSys.getConfig("rfidmanmatchtime");
		if (null != listCfg && !listCfg.isEmpty()){
			rfidmanmatchtime = Integer.valueOf(listCfg.get(0).get("intValue").toString());
			if (rfidmanmatchtime < 5)
				rfidmanmatchtime = 30;
		}
		listCfg = dbSys.getConfig("rfidlistenport");
		if (null != listCfg && !listCfg.isEmpty()){
			portListen = Integer.valueOf(listCfg.get(0).get("intValue").toString());
			if (portListen < 1)
				portListen = 10000;
		}
		flushReaderList();
		//创建监听线程
		(new RFIDReader()).start();
	}
	
	public void start(){
		ListenRun listen = new ListenRun();
		new Thread(listen,"RFIDListen").start();
	}
	
	/*
	 * 定时器执行函数
	 * 向每个连接的RFID发送读取请求
	 *//*
	public void funTimer(String name){
		//向所有已连接的RFID设备发送读取请求
		byte[] sendData0 = buildSendData(0,(byte)1);		//ML-M5000五字节通信协议
		byte[] sendData1 = buildSendData(1,(byte)1);		//原708设备通信协议
		synchronized(readerList){
			for(HashMap reader: readerList){
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
				if (-1== interfaceType){
					continue;
				}
				if (interfaceType >= 2 && interfaceType<=5){	//无源1,2,3,4天线
					//改为由线程处理，每个RFID设备一个线程
					//readPassive(reader,interfaceType - 1);
					continue;
				}
				AsynchronousSocketChannel socketChannel = (AsynchronousSocketChannel)reader.get("socketChannel");
				if (null != socketChannel && socketChannel.isOpen()){
					try{
						if (1 == interfaceType){
							socketChannel.write(ByteBuffer.wrap(sendData1));
						}else if (0 == interfaceType){
							socketChannel.write(ByteBuffer.wrap(sendData0));
						}
					}catch(Exception e){
						e.printStackTrace();
						try{
							socketChannel.shutdownInput();
							socketChannel.shutdownOutput();
						}catch(Exception e2){
						}
					}
				}
			}		
		}
	}
	
	*//*
	 * 组建向RFID设备发送的字节内容
	 *//*
	private byte[] buildSendData(int interfaceType,Byte addr){
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
		}else{							//ML-M5000
			byte[] sendData={0x01,0x04,0x01,0x03};
			if (null != addr)
				sendData[0]=addr;
			return sendData;
		}
	}
	
	*//*
     * Read到请求数据的回调
     *//*
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
				}
			}
        }
 
        @Override
        public void completed(Integer result, AsynchronousSocketChannel attachment) {
            try {
                if (result < 0) {	// 客户端关闭了连接
                	attachment.close();
                	readerInfo.put("socketChannel",null);
        			logger.error("client closed. ip="+attachment.getRemoteAddress().toString());
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
                        e.printStackTrace();
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
    			logger.error("client failed. ip="+attachment.getRemoteAddress().toString());
            }catch(Exception e){
            	e.printStackTrace();
            }
        }
        
        *//*
         * 处理ML-M5000五字节协议返回的内容
         *//*
        private void readData0(AsynchronousSocketChannel attachment)
        	throws Exception{
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
    			logger.error("read first two bytes data are not 0xFF from RFID,ip="+attachment.getRemoteAddress().toString());
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
    			logger.error("card data len is not multiple of 5,ip="+attachment.getRemoteAddress().toString());
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
    			logger.debug("read card no " +cardNo+ " from "+attachment.getRemoteAddress().toString());
    			settleReadCard(cardNo,isLowerPower,readerInfo);
    		}        	
        }
        
        *//*
         * 处理原708设备的协议返回内容
         *//*
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
    			logger.error("read first byte data is not 0x0B from RFID,ip="+attachment.getRemoteAddress().toString());
    			attachment.shutdownInput();
    			attachment.shutdownOutput();
    			return;
    		}
    		if (head[2] < 3 || ((head[2] - 3) % 8) != 0 ){
    			logger.error("data length is wrong from RFID,ip="+attachment.getRemoteAddress().toString());
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
 
    *//*
     * 无源RFID读取
     * @params:
     * 	reader: 设备信息
     * 	antennaNum: 天线个数
     *//*
	private void readPassive(HashMap reader,int antennaNum){
		Reader devReader = new Reader();
		String ip = (String)reader.get("readerIP");
		try{
			if (devReader.InitReader_Notype(ip, antennaNum) != READER_ERR.MT_OK_ERR) {
	        	logger.error("RFID InitReader failed：" + ip);
				return;
			}
		}catch(Exception e){
			e.printStackTrace();
			return;
		}
		int antCount = 0;
		READER_ERR err;
		*//*
		//检测连接的天线
		ConnAnts_ST cast=devReader.new ConnAnts_ST();
		err=devReader.ParamGet(Mtr_Param.MTR_PARAM_READER_CONN_ANTS, cast);
		if (err != READER_ERR.MT_OK_ERR){
        	logger.error("RFID ParamGet failed：" + err.toString()+"("+ip+")");
			return;
		}
		if (cast.antcnt < 1){
        	logger.error("RFID ParamGet antcnt is zero!");
			return;
		}
		antCount = cast.antcnt;
		*//*
		//改成新的天线测试方法
		int[] antsTemp = new int[4];
	    for (int i = 1; i <= 4; i++) {
	    	AntPortsVSWR apvr=devReader.new AntPortsVSWR();
	    	apvr.andid=i;
	    	apvr.power=(short) 3000;
	    	apvr.region=Region_Conf.RG_NA;
	    	//定频
	    	apvr.frecount = 1;
	    	apvr.vswrs[0].frequency = 915250;
	    	err=devReader.ParamGet(Mtr_Param.MTR_PARAM_RF_ANTPORTS_VSWR, apvr);
	    	//antID1234即对应物理天线口1234，当获取的vswr小于10时即可认为天线口有天线连接
	    	//System.out.println(" antID = "+i+" vswr= "+apvr.vswrs[0].vswr+" frequency= "+apvr.vswrs[0].frequency);
			if (err != READER_ERR.MT_OK_ERR){
	        	logger.error("RFID ParamGet for andid="+Integer.toString(i)+" failed：" + err.toString()+"("+ip+")");
			}else if (apvr.vswrs[0].vswr < 10){
				antsTemp[antCount] = i;
				antCount++;
			}
	    }
		if (antCount < 1){
        	logger.error("RFID ParamGet antCount is zero!");
			return;
		}
		int[] ants = new int[antCount];
		for(int i=0;i<antCount;i++){
			ants[i] = antsTemp[i];
		}
		*//*
		//不检测天线
		int[] val=new int[1];
		val[0]=0;
		err=devReader.ParamSet(Mtr_Param.MTR_PARAM_READER_IS_CHK_ANT, val);
		if (err != READER_ERR.MT_OK_ERR){
        	logger.error("RFID ParamSet failed：" + err.toString()+"("+ip+")");
			return;
		}
		*//*
		*//*
		int[] ants=new int[antennaNum];
		for(int i=0;i<antennaNum;i++){
			ants[i] = i+1;
		}
		*//*
		TAGINFO[] tags=new TAGINFO[200];
		int[] tagnum=new int[1];
		short timeout = 1000;		//1秒
		//err = devReader.TagInventory(cast.connectedants, antCount, timeout, tags, tagnum);
		err = devReader.TagInventory(ants,antCount, timeout, tags, tagnum);
		devReader.CloseReader();
		if (err != READER_ERR.MT_OK_ERR){
        	logger.error("RFID TagInventory failed：" + err.toString()+"("+ip+")");
			return;
		}
		logger.debug("RFID ip: "+ip+";天线数："+Integer.toString(antCount)
				+";读到标签数："+Integer.toString(tagnum[0]));
		if (tagnum[0] < 1)
			return;
		for(int i=0;i<tagnum[0];i++){
			try{
				String cardNo = Reader.bytes_Hexstr(tags[i].EpcId); 
				logger.debug(ip+"读到无源标签："+cardNo);
				settleReadCard(cardNo,false,reader);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
   *//*
     * 无源RFID异步读取
     * @params:
     * 	reader: 设备信息
     * 	antennaNum: 天线个数
     *//*
	private static void asyncReadPassive(HashMap reader,int antennaNum){
		ZKYAsynReader asynReader = new ZKYAsynReader();
		if (!asynReader.init(reader,(String)reader.get("readerIP"),antennaNum)){
			return;
		}
		reader.put("asynReader",asynReader);
		if (!asynReader.StartReading()){
			return;
		}
	}

    *//*
     * 从数据库中读取所有RFID探测器
     *//*
    public static synchronized void flushReaderList(){
    	System.out.println("flushReaderList");
		//对于中科院无源RFID设备，先关闭正在运行的读取线程
		stopZKYRead();
		//读取所有RFID探测器
		List<HashMap<String,Object>> listTemp = db.getTableData("rfidreader", null, null);
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
    	System.out.println("flushReaderList complete");
	}
	
	//对于中科院无源RFID设备，先关闭正在运行的读取线程
	private static void stopZKYRead(){
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
	private static void startZKYRead(){
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
					asyncReadPassive(reader,interfaceType - 1);
				}
			}
		}
	}
	
    *//*
     * 根据IP地址匹配对应的RFID探测器
     *//*
    private static HashMap matchedReader(String ip){
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
			try{
				AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(portListen));
				while (true){
		            Future<AsynchronousSocketChannel> future = serverChannel.accept();
		            AsynchronousSocketChannel client = null;
		            try {
		            	client = future.get();
		            	//String clientIP = client.getRemoteAddress().toString();
		            	InetSocketAddress addr = (InetSocketAddress)client.getRemoteAddress();
		            	String clientIP = addr.getAddress().toString();
		            	if (clientIP.startsWith("/"))
		            		clientIP = clientIP.substring(1);
		            	logger.debug("RFID地址：" + clientIP);
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
				            	logger.debug("未匹配到RFID设备，客户端IP地址：" + clientIP);
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
	
    *//*
     * 处理读到的卡号内容
     * @param:
     * 		cardInfo: 读取到的8字节信息
     * 		readerInfo: 对应的RFID探测器信息
     *//*
    public static void settleReadCard(String cardNo, boolean isLowerPower,HashMap readerInfo){
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
    	HashMap hmData = new HashMap();
    	hmData.put("rfidNo",cardNo);
    	//检查编号是否已在库中或绑定
    	List<HashMap<String,Object>> listTemp = db.getTableData("rfidtag", hmData, null);
    	if (null!=listTemp && !listTemp.isEmpty()){
    		hmData.put("isOther",0);
    		hmData.put("isAsset",listTemp.get(0).get("isUsed"));
    	}else{
    		hmData.put("isAsset",0);
    		hmData.put("isOther",1);
    	}
    	hmData.put("readerName",readerInfo.get("readerName"));
    	hmData.put("status", 0);
    	db.insertTableData("rfidrecord", hmData);
    	
    	//低电报警
    	if (isLowerPower){
    		Alarm.addAlarm("卡号为"+cardNo+"的RFID低电", 1, "低电报警", cardNo, null);
    	}
    }
	
	*//*
	 * 定时器执行函数，此定时器一般设置为RFID空闲时间的一半
	 * 分析数据库中读到的RFID记录，确定进出行为
	 *//*
	public void analyseTimer(String name){
		HashMap condition = new HashMap();
		condition.put("status",0);
		List<HashMap<String,Object>> listRecord = db.getTableData("rfidrecord", condition, "rfidNo,timeAdd desc");  //现在改成读资产
		if (null == listRecord || listRecord.isEmpty())
			return;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String curNo="";
		Long startTime = null;
		String startReaderName = null;
		Long endTime = null;
		String endReaderName = null;
		Long curTime = (new Date()).getTime();
		for(HashMap<String,Object> item: listRecord){
			String rfidNo = (String)item.get("rfidNo");
			if (!rfidNo.equals(curNo)){
				//当前的RFID是否需要进出判断
				if (!curNo.isEmpty() && null!=endTime && (curTime - endTime > rfidSplit*1000L)){
					//空闲时间超过设置的值，需要分析
					analyseRFIDRecord(curNo,startReaderName,startTime,endReaderName,endTime);
				}
				
				//下一个RFID
				curNo = rfidNo;
				String timeAdd = (String)item.get("timeAdd");
				try{
					Date dt = sdf.parse(timeAdd);
					endTime = dt.getTime();					
				}catch(Exception e){
					e.printStackTrace();
				}
				endReaderName = (String)item.get("readerName");
				startTime = endTime;
				startReaderName = endReaderName;
			}else{
				String timeAdd = (String)item.get("timeAdd");
				try{
					Date dt = sdf.parse(timeAdd);
					Long time = dt.getTime();
					if (startTime - time < rfidSplit*1000L){
						startTime = time;
						startReaderName = (String)item.get("readerName");
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		if (!curNo.isEmpty() && null!=endTime && (curTime - endTime >= rfidSplit*1000L)){
			//空闲时间超过设备的值，需要分析
			analyseRFIDRecord(curNo,startReaderName,startTime,endReaderName,endTime);
		}		
	}
	
	*//*
	 * 分析RFID进出
	 *//*
	private void analyseRFIDRecord(String rfidNo,
			String startReaderName,Long startTime,
			String endReaderName,Long endTime){
		int result = 3;		//缺省为忽略
		HashMap condition = new HashMap();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		//匹配资产
		condition.put("rfidNo", rfidNo);
		condition.put("delStatus", 0);
		HashMap<String,Object> asset = db.getTableDataOne("asset", condition);
		if (null != asset && !asset.isEmpty()){
			condition.clear();
			condition.put("startReader",startReaderName);
			if (endReaderName.equals(startReaderName)){
				condition.put("endReader","");
			}else{
				condition.put("endReader",endReaderName);
			}
			List<HashMap<String,Object>> readerSet = db.getTableData("rfidreaderset", condition, null);  //查的RFID进出口设置表entryPos?
			if (null != readerSet && !readerSet.isEmpty()){
				//有进出设置
				HashMap<String,Object> set = readerSet.get(0);
				int curStatus = Integer.valueOf(set.get("directory").toString());   //0->进;  1->出
				result = curStatus+1;
				
				//记录资产进出
				HashMap hmData = new HashMap();
				hmData.put("assetID",asset.get("assetID"));
				hmData.put("doorName",set.get("doorName"));
				hmData.put("directory",set.get("directory"));
				int preStatus = Integer.parseInt(asset.get("curStatus").toString());
				hmData.put("preStatus",preStatus);
				hmData.put("timePreStatus",asset.get("timeStatus"));
				long hours = 0L;
				String timeStatus = (String)asset.get("timeStatus");
				try{
					Long statusLong = sdf.parse(timeStatus).getTime();
					Long curLong = (new Date()).getTime();
					hours = (curLong - statusLong + 1800*1000L)/(3600*1000L);
				}catch(Exception e){
					e.printStackTrace();
				}
				hmData.put("statusTimes",hours);
				hmData.put("statusMatched",curStatus == preStatus?1:0);	//状态相同，表示原状态有问题
				String linkCamera = (String)set.get("linkCamera");
				hmData.put("linkCamera",linkCamera);
				if (null == linkCamera || linkCamera.isEmpty())
					hmData.put("staffMatched",1);	//没有关联摄像头，直接设置为正常
				else
					hmData.put("staffMatched",0);
				db.insertTableData("assetrecord", hmData);
				
				//更新资产状态
				hmData.clear();
				hmData.put("assetID",asset.get("assetID"));
				hmData.put("curStatus",curStatus);
				hmData.put("timeStatus",sdf.format(new Date()));
				hmData.put("isAbnormal",curStatus == preStatus?1:0);  //0;  1->异常
				hmData.put("reasonType",curStatus == preStatus?2:0); //1异常外出;2进出状态不匹配;3外出超过天数
				db.updateTableData("asset", hmData);
				
				//直接报警或资产出去即报警
				int isAlarm = Integer.valueOf(set.get("isAlarm").toString());
				if (isAlarm != 1 && curStatus == DIRECTORY_OUT){
					isAlarm = Integer.valueOf(asset.get("outAlarm").toString());   //取的asset表中的哪个字段
				}
				if (isAlarm == 1){
					StringBuilder sbAlarm = new StringBuilder();
					sbAlarm.append("资产");
					sbAlarm.append(asset.get("name"));
					sbAlarm.append("在位置");
					sbAlarm.append(set.get("doorName"));
					sbAlarm.append(curStatus==0?"进入":"出去");
		    		Alarm.addAlarm(sbAlarm.toString(), 1, "资产进出", "assetID", 
		    				Long.valueOf(asset.get("assetID").toString()));
				}
			}
		}
		
		//更新RFID记录
		HashMap hmData = new HashMap();
		hmData.put("rfidNo",rfidNo);
		hmData.put("endTime",sdf.format(new Date(endTime+1000)));
		hmData.put("status",result);
		db.updateExt("upRfidRecordStatus", hmData);

		//内存中读取记录清除，重新读取
		synchronized(readerList){
	    	for(HashMap item: readerList){
	        	HashMap<String,Long> readedCard = (HashMap<String,Long>)item.get("readedCard");
	        	if (null != readedCard){
					synchronized(readedCard){
						readedCard.remove(rfidNo);
					}
	        	}
	    	}
	    }
	}

	*//*
	 * 定时检查资产进出与员工人脸的关联
	 * 频率一般设置成匹配间隔时间的一半
	 *//*
	public void matchRFIDMan(String name){
		HashMap condition = new HashMap();
		condition.put("staffMatched",0);
		List<HashMap<String,Object>> assetRecord = db.selectExt("selAssetRecord", condition);
		if (null == assetRecord)
			return;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(HashMap<String,Object> item : assetRecord){
			Object temp = item.get("staffID");
			String linkCamera = (String)item.get("linkCamera");
			if(null == temp || null==linkCamera || linkCamera.isEmpty()){
				condition.clear();
				condition.put("autoID",item.get("autoID"));
				condition.put("staffMatched", 4);		//未关联摄像头，设置为未设置保管员
				db.updateTableData("assetrecord", condition);
				continue;
			}
			Long staffID = Long.valueOf(temp.toString());
			Long timeRecord = null;
			try{
				String timeAdd = (String)item.get("timeAdd");
				Date time = sdf.parse(timeAdd);
				timeRecord = time.getTime();
			}catch(Exception e){
				e.printStackTrace();
				continue;
			}
			String startTime = sdf.format(new Date(timeRecord - rfidmanmatchtime*1000L));
			String endTime = sdf.format(new Date(timeRecord + rfidmanmatchtime*1000L));
			condition.clear();
			condition.put("cameraName",linkCamera);
			condition.put("startTime", startTime);
			condition.put("endTime", endTime);
			List<HashMap<String,Object>> staffRecord = db.selectExt("selStaffRecord", condition);
			int matched = 3;	//未发现员工
			Object matchedStaffRecord = null;
			if (null != staffRecord && !staffRecord.isEmpty()){
				matched = 2;	//非保管员
				for(HashMap<String,Object> staff: staffRecord){
					if (staffID.equals(staff.get("staffID"))){
						matched = 1;	//正常
						matchedStaffRecord = staff.get("autoID");
						break;
					}
				}
			}
			if (1 == matched){
				condition.clear();
				condition.put("autoID",item.get("autoID"));
				condition.put("staffMatched", 1);
				condition.put("matchedStaff", matchedStaffRecord);
				db.updateTableData("assetrecord", condition);				
			}else{
				//时间是否已过了间隔时间
				Long curTime = (new Date()).getTime();
				if (curTime - timeRecord >= rfidmanmatchtime*1000L){
					condition.clear();
					condition.put("autoID",item.get("autoID"));
					condition.put("staffMatched", matched);
					db.updateTableData("assetrecord", condition);
					if (Integer.parseInt(item.get("staffAlarm").toString())==1){
						StringBuilder sbAlarm = new StringBuilder();
						sbAlarm.append("资产");
						sbAlarm.append(item.get("name"));
						sbAlarm.append("在位置");
						sbAlarm.append(item.get("doorName"));
						int directory = Integer.parseInt(item.get("directory").toString());
						sbAlarm.append(directory==0?"进入":"出去");
			    		Alarm.addAlarm(sbAlarm.toString(), 1, "未发现保管员", "autoID", 
			    				Long.valueOf(item.get("autoID").toString()));
					}
				}
			}
		}
	}	
	
	*//*
	 * 定时检查资产外出超过天数
	 *//*
	public void checkOutDays(String name){
		DBSystem dbSys = new DBSystem();
		List<HashMap<String,Object>> listCfg = dbSys.getConfig("assetoutmaxday");
		int maxDays = 0;
		if (null != listCfg && !listCfg.isEmpty()){
			maxDays = Integer.valueOf(listCfg.get(0).get("intValue").toString());
		}
		if (maxDays<=0)
			return;
		Long curTime = (new Date()).getTime();
		curTime -= (maxDays -1 )*24*3600*1000L;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String endDate = sdf.format(new Date(curTime));
		HashMap condition = new HashMap();
		condition.put("curStatus", 1);
		condition.put("notReasonType", 1);
		condition.put("timeStatusEnd", endDate);
		List<HashMap<String,Object>> assetList = db.selectExt("selAsset", condition);
		if (null != assetList && !assetList.isEmpty()){
			HashMap hmData = new HashMap();
			for(HashMap<String,Object> asset: assetList){
				hmData.clear();
				Long assetID = Long.valueOf(asset.get("assetID").toString());
				hmData.put("assetID",assetID);
				hmData.put("isAbnormal",1);
				hmData.put("reasonType",1);
				db.updateTableData("asset", hmData);
				StringBuilder sbAlarm = new StringBuilder();
				sbAlarm.append("资产");
				sbAlarm.append(asset.get("name"));
				sbAlarm.append("(资产编号：");
				sbAlarm.append(asset.get("assetNo"));
				sbAlarm.append(";RFID号码：");
				sbAlarm.append(asset.get("rfidNo"));
				sbAlarm.append(")外出达到");
				sbAlarm.append(maxDays);
				sbAlarm.append("天");
	    		Alarm.addAlarm(sbAlarm.toString(), 1, "资产外出超过天数", "assetID", assetID);
			}
		}
	}*/

 }
