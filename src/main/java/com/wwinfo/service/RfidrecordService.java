package com.wwinfo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wwinfo.model.Rfidrecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wwinfo.pojo.query.RfidrecordQuery;

/**
 * <p>
 * RFID阅读记录表 服务类
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
public interface RfidrecordService extends IService<Rfidrecord> {

    /**
     * 分页获取RFID阅读记录
     * @param rfidrecordQuery
     * @return
     */
    IPage listPage(RfidrecordQuery rfidrecordQuery);
}
