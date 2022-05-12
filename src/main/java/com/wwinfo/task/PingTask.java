package com.wwinfo.task;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.wwinfo.mapper.TConfigMapper;
import com.wwinfo.model.Alarm;
import com.wwinfo.model.Rfidreader;
import com.wwinfo.model.TConfig;
import com.wwinfo.service.AlarmService;
import com.wwinfo.service.RfidreaderService;
import com.wwinfo.util.BusinUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * Description: 阅读器在线检测
 *
 * @Author: lk
 * DateTime: 2022-03-07 16:03
 */
@Slf4j
//@Configuration
public class PingTask implements SchedulingConfigurer {

    @Autowired
    private RfidreaderService rfidreaderService;

    @Autowired
    private AlarmService alarmService;

    @Autowired
    private BusinUtil businUtil;

    @Resource
    private TConfigMapper configMapper;

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

    //@Scheduled(cron="#{@getCronValue(ping)}")
    //@Scheduled(cron="${task.ping}")
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        //log.info("阅读器在线检测开始执行执行......" + LocalDateTime.now().toLocalTime());
        task();
        taskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
                () -> log.info("configureTasks()阅读器在线检测开始执行: " + DateUtil.now()),
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    //2.1 从数据库获取执行周期
                    TConfig pingconfig = configMapper.getConfigByfieldName("ping");
                    String cron = "0/30 * * * * ?";
                    if (pingconfig != null) {
                        cron = "0/" + pingconfig.getValue() + " * * * * ?";
                    }
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );
    }

    private void task(){
        try {
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
                            TConfig mailTos = configMapper.getConfigByfieldName("mailTos");
                            Optional.ofNullable(mailTos).ifPresent(e -> mailtos = mailTos.getValue());
                            alarm.setEmail(mailtos);
                            alarmService.add(alarm);

                            log.info("开始发送报警邮件");
                            businUtil.sendMail("RFID探测器断线报警", content.toString());
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("阅读器在线检测异常: {}", e);
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
            log.error("ping()异常: {}", e);
        }
        return ret;
    }
}
