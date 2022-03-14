package com.wwinfo.task;

import cn.hutool.core.collection.CollUtil;
import com.wwinfo.model.Alarm;
import com.wwinfo.model.Rfidreader;
import com.wwinfo.model.Rfidrecord;
import com.wwinfo.service.AlarmService;
import com.wwinfo.service.RfidreaderService;
import com.wwinfo.service.RfidrecordService;
import com.wwinfo.util.BusinUtil;
import com.wwinfo.util.RFIDReaderUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 定时向每个连接的RFID发送读取请求
 *
 * @Author: lk
 * DateTime: 2022-03-08 14:59
 */
@Slf4j
@Component
public class RFIDSend implements ApplicationRunner {

    private static int rfidSameTime = 3;	//同一卡号视为一次的间隔时间

    @Autowired
    private RfidrecordService rfidrecordService;

    @Autowired
    private RfidreaderService rfidreaderService;

    @Autowired
    private AlarmService alarmService;

    @Autowired
    private RFIDReaderUtil rfidReaderUtil;

    @Autowired
    private BusinUtil businUtil;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("run ...........");
        rfidReaderUtil.init();
    }


    @Scheduled(cron="${task.rfidsend}")
    public void pullInfo() {
        log.info("task start............");
        //向所有已连接的RFID设备发送读取请求
        byte[] sendData0 = rfidReaderUtil.buildSendData(0,(byte)1);		//ML-M5000五字节通信协议
        byte[] sendData1 = rfidReaderUtil.buildSendData(1,(byte)1);		//原708设备通信协议
        List<HashMap> readerList = rfidReaderUtil.getReaderList();
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

   /* *//*
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
    }*/

    /*
     * Read到请求数据的回调
     */
   /* private class ReadHandler implements CompletionHandler<Integer, AsynchronousSocketChannel> {
        public HashMap readerInfo;        //读卡器信息
        private ByteBuffer buffer;        //读取缓冲内存
        private int interfaceType = 0;    //读卡器类型

        public ReadHandler(HashMap reader, ByteBuffer buffer) {
            this.readerInfo = reader;
            this.buffer = buffer;
            Object temp = reader.get("interfaceType");
            if (null != temp) {
                try {
                    interfaceType = Integer.valueOf(temp.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void completed(Integer result, AsynchronousSocketChannel attachment) {
            try {
                if (result < 0) {    // 客户端关闭了连接
                    attachment.close();
                    readerInfo.put("socketChannel", null);
                    log.error("client closed. ip: {}", attachment.getRemoteAddress().toString());
                    return;
                } else if (result == 0) {    // 空数据
                } else {
                    try {
                        if (interfaceType == 1) {
                            readData1(attachment);
                        } else if (interfaceType == 0) {
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
            try {
                attachment.close();
                readerInfo.put("socketChannel", null);
                log.error("client failed. ip: {}", attachment.getRemoteAddress().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        *//*
         * 处理ML-M5000五字节协议返回的内容
         *//*
        private void readData0(AsynchronousSocketChannel attachment)
                throws Exception {
            if (buffer.position() < 6) {
                //还没有6个字符，等待下次
                return;
            }
            int pos = buffer.position();    //保存当前已获取数据的位置
            //开始从头读取
            buffer.flip();
            byte[] head = new byte[4];
            buffer.get(head);
            if (0xFF != (head[0] & 0xFF) || 0xFF != (head[1] & 0xFF)) {
                log.error("read first two bytes data are not 0xFF from RFID,ip=" + attachment.getRemoteAddress().toString());
                attachment.shutdownInput();
                attachment.shutdownOutput();
                return;
            }
            //检查是否读取到EEEE结束标记
            int dataLen = buffer.remaining();        //剩余的数据长度
            byte[] cardData = new byte[dataLen];        //保存卡号数据，每5个字节为一个卡号
            int cardDataLen = 0;                    //卡号数据的实际长度
            boolean hasEnd = false;                    //是否有结束标记
            byte b1 = buffer.get();
            byte b2 = 0;
            int i = 1;
            do {
                b2 = buffer.get();
                if (0xEE == (b1 & 0xFF) && 0xEE == (b2 & 0xFF)) {
                    hasEnd = true;
                    break;
                }
                cardData[cardDataLen] = b1;
                cardDataLen++;
                b1 = b2;
                i++;
            } while (i < dataLen);
            if (!hasEnd) {
                //没有结束标记，等下次处理
                buffer.clear();    //不会清除数据
                buffer.position(pos);
                return;
            }
            buffer.compact();        //剩余字节移到最前面，供下次处理
            if (cardDataLen < 1) {
                //没有卡号
                return;
            }
            if (cardDataLen % 5 != 0) {
                //卡号字节数不是5的倍数
                log.error("card data len is not multiple of 5,ip=" + attachment.getRemoteAddress().toString());
                return;
            }
            int number = cardDataLen / 5;
            for (i = 0; i < number; i++) {
                byte cardType = cardData[i * 5];
                boolean isLowerPower = ((cardType & 0x80) == 0x80);
                cardType &= 0x7F;
                String cardNo = "";
                if (cardType == 0x05) {
                    //双频卡
                    byte[] btTemp = new byte[4];
                    btTemp[0] = btTemp[1] = 0;
                    btTemp[2] = cardData[i * 4 + 3];
                    btTemp[3] = cardData[i * 4 + 4];
                    cardNo = Integer.toString(ByteBuffer.wrap(btTemp).getShort() & 0xFFFF);
                } else if (cardType == 0x01) {
                    //单频卡
                    byte[] btTemp = new byte[4];
                    for (int j = 0; j < 4; j++)
                        btTemp[j] = cardData[i * 5 + 1 + j];
                    cardNo = Long.toString(ByteBuffer.wrap(btTemp).getInt() & 0x0FFFFFFFFL);
                }
                log.debug("read card no " + cardNo + " from " + attachment.getRemoteAddress().toString());
                settleReadCard(cardNo, isLowerPower, readerInfo);
            }
        }

        *//*
         * 处理原708设备的协议返回内容
         *//*
        private void readData1(AsynchronousSocketChannel attachment)
                throws Exception {
            if (buffer.position() < 3) {
                //还没有3个字符，等待下次
                return;
            }
            int pos = buffer.position();    //保存当前已获取数据的位置
            //开始从头读取
            buffer.flip();
            byte[] head = new byte[3];
            buffer.get(head);
            if (0x0B != head[0]) {
                log.error("read first byte data is not 0x0B from RFID,ip: {}", attachment.getRemoteAddress().toString());
                attachment.shutdownInput();
                attachment.shutdownOutput();
                return;
            }
            if (head[2] < 3 || ((head[2] - 3) % 8) != 0) {
                log.error("data length is wrong from RFID,ip: {}", attachment.getRemoteAddress().toString());
                attachment.shutdownInput();
                attachment.shutdownOutput();
                return;
            }
            int number = (head[2] - 3) / 8;    //读到的RFID个数
            if (buffer.remaining() < head[2]) {
                //数据还不够，等下次处理
                buffer.clear();    //不会清除数据
                buffer.position(pos);
                return;
            }
            buffer.position(buffer.position() + 2);    //移动两个字节
            for (int i = 0; i < number; i++) {
                byte[] cardInfo = new byte[8];
                buffer.get(cardInfo);        //每个卡信息为8个字节
                //把字节数转换卡号和低电信息
                byte[] btTemp = new byte[4];
                for (int j = 0; j < 4; j++)
                    btTemp[j] = cardInfo[j];
                String cardNo = Long.toString(ByteBuffer.wrap(btTemp).getInt() & 0x0FFFFFFFFL);
                //第5字节表示是否低电压
                boolean isLowerPower = (cardInfo[4] != 0);

                settleReadCard(cardNo, isLowerPower, readerInfo);
            }
            buffer.compact();        //剩余字节移到最前面，供下次处理
        }
    }*/

    /*
     * 处理读到的卡号内容
     * @param:
     * 		cardInfo: 读取到的8字节信息
     * 		readerInfo: 对应的RFID探测器信息
     */
   /* public void settleReadCard(String cardNo, boolean isLowerPower,HashMap readerInfo){
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
    }*/

}
