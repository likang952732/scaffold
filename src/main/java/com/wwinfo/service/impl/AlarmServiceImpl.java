package com.wwinfo.service.impl;

import com.wwinfo.model.Alarm;
import com.wwinfo.mapper.AlarmMapper;
import com.wwinfo.service.AlarmService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
