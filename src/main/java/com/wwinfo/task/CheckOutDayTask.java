package com.wwinfo.task;

import com.wwinfo.mapper.AssetMapper;
import com.wwinfo.model.Alarm;
import com.wwinfo.model.Asset;
import com.wwinfo.model.TConfig;
import com.wwinfo.service.AlarmService;
import com.wwinfo.service.TConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description: 定时检查资产外出超过天数
 * @Author: lk
 * DateTime: 2022-03-07 21:32
 */
@Slf4j
//@Component
public class CheckOutDayTask {

    @Autowired
    private TConfigService configService;

    @Autowired
    private AlarmService alarmService;

    @Resource
    private AssetMapper assetMapper;

    @Scheduled(cron="${task.checkOutDay}")
    public void check() {
        TConfig config = configService.getConfig("maxDays");
        int maxDays = 0;
        if (config != null){
            maxDays = Integer.valueOf(config.getValue());
        }
        if (maxDays<=0)
            return;
        Long curTime = (new Date()).getTime();
        curTime -= (maxDays -1 )*24*3600*1000L;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String endDate = sdf.format(new Date(curTime));
        Map<String, Object> condition = new HashMap();
        condition.put("curStatus", 1);
      /*  condition.put("notReasonType", 1);*/
        condition.put("timeStatusEnd", endDate);
        List<Asset> assetList = assetMapper.getAssetByMap(condition);
        if (null != assetList && !assetList.isEmpty()){
            for(Asset asset: assetList){
                asset.setIsAbnormal(1);
                asset.setReasonType(1);
                assetMapper.updateById(asset);

                StringBuilder sbAlarm = new StringBuilder();
                sbAlarm.append("资产");
                sbAlarm.append(asset.getName());
                sbAlarm.append("(资产编号：");
                sbAlarm.append(asset.getAssetNo());
                sbAlarm.append(")外出达到");
                sbAlarm.append(maxDays);
                sbAlarm.append("天");

                Alarm alarm = new Alarm();
                alarm.setContent(sbAlarm.toString());
                alarm.setAlarmType(1);
                alarm.setAlarmEmail(0);
                alarm.setIsSend(0);
                alarmService.add(alarm);
            }
        }
    }
}
