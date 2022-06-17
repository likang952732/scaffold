package com.wwinfo.task;


import com.wwinfo.util.RFIDReaderUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
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
    private RFIDReaderUtil rfidReaderUtil;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        rfidReaderUtil.init();
    }


    @Scheduled(cron="${task.rfidsend}")
    public void pullInfo() {
        log.info("pullInfo()开始执行");
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

}
