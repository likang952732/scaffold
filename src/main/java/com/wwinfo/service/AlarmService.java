package com.wwinfo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wwinfo.model.Alarm;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wwinfo.pojo.query.AlarmQuery;

/**
 * <p>
 * 所有报警记录表 服务类
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
public interface AlarmService extends IService<Alarm> {

    /**
     * 分页获取所有报警记录
     * @param alarmQuery
     * @return
     */
    IPage listPage(AlarmQuery alarmQuery);

}
