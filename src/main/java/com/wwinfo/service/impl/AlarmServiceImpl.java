package com.wwinfo.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwinfo.common.ExcludeEmptyQueryWrapper;
import com.wwinfo.model.Alarm;
import com.wwinfo.mapper.AlarmMapper;
import com.wwinfo.pojo.query.AlarmQuery;
import com.wwinfo.service.AlarmService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 所有报警记录表 服务实现类
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Service
public class AlarmServiceImpl extends ServiceImpl<AlarmMapper, Alarm> implements AlarmService {

    @Resource
    private AlarmMapper alarmMapper;

    @Override
    public IPage listPage(AlarmQuery alarmQuery) {
        Page<Alarm> page = new Page<>(alarmQuery.getPageNum(), alarmQuery.getPageSize());
    /*    QueryWrapper<Alarm> wrapper = new QueryWrapper<>();
        wrapper.eq(alarmQuery.getAlarmType()!= null , "alarmType", alarmQuery.getAlarmType());
        wrapper.eq(alarmQuery.getAlarmEmail()!= null, "alarmEmail", alarmQuery.getAlarmEmail());
        wrapper.eq(alarmQuery.getIsSend()!=null, "isSend", alarmQuery.getIsSend());
        wrapper.ge(StrUtil.isNotBlank(alarmQuery.getStartTime()), "timeAdd", alarmQuery.getStartTime());
        wrapper.le(StrUtil.isNotBlank(alarmQuery.getEndTime()),"timeAdd", alarmQuery.getEndTime());
        wrapper.orderByDesc("timeAdd");*/
        return alarmMapper.getPage(page, alarmQuery);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int add(Alarm alarm) {
        return alarmMapper.insert(alarm);
    }

}
