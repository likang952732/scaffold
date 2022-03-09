package com.wwinfo.task;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.mail.MailUtil;
import com.wwinfo.model.Alarm;
import com.wwinfo.model.Rfidreader;
import com.wwinfo.service.AlarmService;
import com.wwinfo.service.RfidreaderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 阅读器在线检测
 *
 * @Author: lk
 * DateTime: 2022-03-07 16:03
 */
@Slf4j
//@Component
public class PingTask {

    @Autowired
    private RfidreaderService rfidreaderService;

    @Autowired
    private AlarmService alarmService;

    @Value("${mail.tos}")
    private String mailtos;

    private static boolean isWindows = true;

    static{
        String strOS = System.getProperty("os.name");
        if (strOS.indexOf("Windows")>=0){
            isWindows = true;
        }else
            isWindows = false;
    }

   // @Scheduled(cron="#{@getCronValue(ping)}")  //5分钟
    @Scheduled(cron="${task.ping}")
    public void ping() {
        List<Rfidreader> readerList = rfidreaderService.list(null);
        if (CollUtil.isNotEmpty(readerList)) {
            for (Rfidreader rf : readerList) {
                String ip = rf.getReaderIP();
                if (StrUtil.isNotBlank(ip)) {
                    boolean pingResult = ping(ip);
                    if (!pingResult) {
                        for (int i = 0; i < 3; i++) {
                            try {
                                Thread.sleep(3000);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            pingResult = ping(ip);
                            if (pingResult)
                                break;
                        }
                    }
                    int lastStatus = rf.getLastStatus();
                    rf.setCheckTime(new Date());
                    rf.setLastStatus(pingResult ? 0 : 1);
                    rfidreaderService.update(rf);

                    if (!pingResult && lastStatus == 0 && rf.getIsAlarm() == 1) {
                        Alarm alarm = new Alarm();
                        //断线报警
                        StringBuilder content = new StringBuilder();
                        content.append("RFID探测器: ");
                        content.append(rf.getReaderName());
                        content.append("(IP地址为: ");
                        content.append(ip);
                        content.append(")在线检测断开");
                        alarm.setContent(content.toString());
                        alarm.setAlarmLevel(0);
                        alarm.setAlarmType(0);
                        alarm.setAlarmEmail(1);
                        alarm.setIsSend(1);
                        alarm.setEmail(mailtos);
                        alarmService.add(alarm);

                        //发送邮件
                        MailUtil.send(mailtos, "RFID探测器断线报警", content.toString(), false);
                    }
                }
            }
        }
    }

    /*
     * 通过PING检测IP地址是否通
     */
    private boolean ping(String ip){
        String cmd = "ping -n 1 ";
        if (!isWindows){
            cmd = "ping -c 1 ";
        }
        cmd += ip;
        boolean ret = false;
        try{
            Process p1 = Runtime.getRuntime().exec(cmd);
            int returnVal = p1.waitFor();
            if(returnVal==0){
                InputStream is = p1.getInputStream();
                byte[] bufferTemp=new byte[2048];
                is.read(bufferTemp);
                String str = new String(bufferTemp,"GBK");
                if (str.indexOf("请求超时")>=0 || str.indexOf("无法访问目标主机")>=0 ||
                        str.indexOf("Request timeout")>=0 ||
                        str.indexOf("could not find host")>=0){
                    ret = false;
                }else{
                    ret = true;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return ret;
    }
}
