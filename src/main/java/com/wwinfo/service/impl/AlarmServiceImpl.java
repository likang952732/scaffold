package com.wwinfo.service.impl;

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
        QueryWrapper<Alarm> wrapper = new ExcludeEmptyQueryWrapper<>();
        wrapper.eq("alarmType", alarmQuery.getAlarmType());
        wrapper.eq("alarmEmail", alarmQuery.getAlarmEmail());
        wrapper.eq("isSend", alarmQuery.getIsSend());
        wrapper.ge("timeAdd", alarmQuery.getStartTime());
        wrapper.le("timeAdd", alarmQuery.getEndTime());
        return alarmMapper.selectPage(page, wrapper);
    }

}
