package com.wwinfo.task;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wwinfo.mapper.AssetoutregMapper;
import com.wwinfo.model.Alarm;
import com.wwinfo.model.Asset;
import com.wwinfo.model.Assetoutreg;
import com.wwinfo.model.Assetrecord;
import com.wwinfo.service.*;
import com.wwinfo.util.RFIDReaderUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description: 此定时器一般设置为RFID空闲时间的一半,分析数据库中读到的RFID记录，确定进出行为
 * @Author: lk
 * DateTime: 2022-03-09 9:30
 */
@Slf4j
@Component
public class AnalysisRFIDRecordTask {

    @Autowired
    private AssetService assetService;

    @Autowired
    private EntryposService entryposService;

    @Autowired
    private AssetrecordService assetrecordService;

    @Autowired
    private AlarmService alarmService;

    @Autowired
    private RfidrecordService rfidrecordService;

    @Autowired
    private RFIDReaderUtil rfidReaderUtil;

    @Resource
    private AssetoutregMapper assetoutregMapper;

    @Value("${mail.tos}")
    private String mailtos;

    public static final int DIRECTORY_OUT = 1;
    private static int  rfidSplit = 60;		//RFID进出空闲时间，即这段时间内没有再读取到后进行进出分析

    @Scheduled(cron="${task.analysisrfid}")
    public void analysis() {
        log.info("AnalysisRFIDRecordTask开始执行");
        Map<String, Object> condition = new HashMap();
        condition.put("status",0);
        List<HashMap<String,Object>> assetList = assetService.getAllRFIDRecord(condition);
        log.info("analysis()中assetList size: {}", assetList.size());
        if (CollUtil.isEmpty(assetList))
            return;
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String curNo="";
        String rfidNo = "";
        Long startTime = null;
        String startReaderName = null;
        Long endTime = null;
        String endReaderName = null;
        Long curTime = (new Date()).getTime();
        Long startReaderID = null;
        Long endReaderID = null;
        Long rfidRecordID = null;
        for (HashMap<String,Object> item: assetList) {
            String assetNo = (String)item.get("assetNo");
            rfidNo = (String)item.get("rfidNo");
            rfidRecordID = (Long)item.get("rfidRecordID");
            if (!assetNo.equals(curNo)) {
                //当前的asset是否需要进出判断
                if (!curNo.isEmpty() && null!=endTime && (curTime - endTime > rfidSplit*1000L)){
                    //空闲时间超过设置的值，需要分析
                    analyseRFIDRecord(curNo,startReaderID,startTime,endReaderID,endTime, rfidNo, rfidRecordID);
                }
                //下一个asset
                curNo = assetNo;
                String timeAdd = LocalDateTimeUtil.format((LocalDateTime)item.get("timeAdd"), DatePattern.NORM_DATETIME_PATTERN);
                endTime = DateUtil.parse(timeAdd).getTime();
                //endReaderName = (String)item.get("readerName");
                endReaderID = (Long)item.get("readerID");
                startTime = endTime;
                //startReaderName = endReaderName;
                startReaderID = endReaderID;
            } else {
                String timeAdd = LocalDateTimeUtil.format((LocalDateTime)item.get("timeAdd"), DatePattern.NORM_DATETIME_PATTERN);
                Long time = DateUtil.parse(timeAdd).getTime();
                if (startTime - time < rfidSplit*1000L){
                    startTime = time;
                    //startReaderName = (String)item.get("readerName");
                    startReaderID = (Long)item.get("readerID");
                }
            }
        }
        if (!curNo.isEmpty() && null != endTime && (curTime - endTime >= rfidSplit*1000L)) {
            //空闲时间超过设备的值，需要分析
            analyseRFIDRecord(curNo,startReaderID,startTime,endReaderID,endTime, rfidNo, rfidRecordID);
        }
    }

    /*
     * 分析asset进出
     */
    private void analyseRFIDRecord(String assetNo, Long startReaderID, Long startTime, Long endReaderID,
                                   Long endTime, String rfidNo, Long rfidRecordID) {
        int result = 3;		//缺省为忽略
        Map<String, Object> condition = new HashMap();
        //匹配资产
        Asset asset = assetService.getOne(new QueryWrapper<Asset>().lambda().eq(Asset::getAssetNo, assetNo).eq(Asset::getDelStatus, 0));
        if (null != asset) {
            condition.clear();
            condition.put("startReaderID",startReaderID);
            condition.put("endReader",endReaderID);
            if (endReaderID.equals(startReaderID)) {
                condition.put("endReader","");
            }
            List<HashMap<String, Object>> readerSet = entryposService.getByParam(condition);
            if (CollUtil.isNotEmpty(readerSet)) {
                //有进出设置
                Map<String, Object> set = readerSet.get(0);
                int curStatus = Integer.valueOf(set.get("directory").toString());
                result = curStatus + 1;

                //记录资产进出
                Assetrecord assetRecord = new Assetrecord();
                assetRecord.setAssetID(asset.getID());
                assetRecord.setPosID(Long.valueOf(set.get("ID").toString()));
                assetRecord.setDirectory(Integer.valueOf(set.get("directory").toString()));
                int preStatus = asset.getCurStatus();
                assetRecord.setPreStatus(preStatus);
                assetRecord.setTimePreStatus(asset.getTimeStatus());
                long hours = 0L;
			/*	String timeStatus = asset.getTimeStatus();
						(String)asset.get("timeStatus");*/
                try {
                    Long statusLong = asset.getTimeStatus().getTime();
                    Long curLong = (new Date()).getTime();
                    hours = (curLong - statusLong + 1800*1000L)/(3600*1000L);
                } catch(Exception e) {
                    e.printStackTrace();
                }
                assetRecord.setStatusTimes(Integer.valueOf(hours+""));
                assetRecord.setStatusMatched(curStatus == preStatus ?1 :0);  //状态相同，表示原状态有问题
                assetRecord.setTimeAdd(DateUtil.date());
                assetRecord.setIsAlarm(Integer.valueOf(set.get("isAlarm").toString()));
                assetrecordService.add(assetRecord);

                //更新资产状态
                asset.setCurStatus(curStatus);
                asset.setTimeStatus(new Date());
                asset.setIsAbnormal(curStatus == preStatus?1:0);
                asset.setReasonType(curStatus == preStatus?2:0);
                asset.setTimeModify(new Date());
                assetService.updateById(asset);

                //判断资产借出是否在进出时间段范围内
                Assetoutreg assetoutreg = assetoutregMapper.selectOne(new QueryWrapper<Assetoutreg>().lambda().eq(Assetoutreg::getAssetID, asset.getID()));
                long curtime = DateUtil.date().getTime();
                boolean outAlarmFlag = true;
                if(assetoutreg != null && curtime >= assetoutreg.getLendStart().getTime() && curtime <= assetoutreg.getLendEnd().getTime()){
                    outAlarmFlag = false;
                }

                //直接报警或资产出去即报警
                int isAlarm = Integer.valueOf(set.get("isAlarm").toString());
                if (isAlarm != 1 && curStatus == DIRECTORY_OUT) {
                    //isAlarm = Integer.valueOf(asset.get("outAlarm").toString());
                    isAlarm = asset.getCurStatus();
                }
                if (isAlarm == 1 && outAlarmFlag) {
                    StringBuilder sbAlarm = new StringBuilder();
                    sbAlarm.append("资产")
                           .append(asset.getName())
                            .append("在位置")
                           .append(set.get("posName"))
                           .append(curStatus == 0 ? "进入": "出去");

                    Alarm alarm = new Alarm();
                    alarm.setContent(sbAlarm.toString());
                    alarm.setAlarmLevel(0);
                    alarm.setAlarmType(0);
                    alarm.setAlarmEmail(1);
                    alarm.setIsSend(1);
                    alarm.setEmail(mailtos);
                    alarmService.add(alarm);
                }
            }
        }

        //更新RFID记录
        Map<String, Object> hmData = new HashMap();
        hmData.put("ID",rfidRecordID);
        hmData.put("rfidNo",rfidNo);
        //hmData.put("endTime",sdf.format(new Date(endTime+1000)));
        hmData.put("status",result);
        rfidrecordService.updateByMap(hmData);

        //内存中读取记录清除，重新读取
        List<HashMap> readerList = rfidReaderUtil.getReaderList();
        synchronized(readerList) {
            for(HashMap item: readerList) {
                HashMap<String,Long> readedCard = (HashMap<String,Long>)item.get("readedCard");
                if (null != readedCard) {
                    synchronized(readedCard) {
                        readedCard.remove(rfidNo);
                    }
                }
            }
        }
    }
}
