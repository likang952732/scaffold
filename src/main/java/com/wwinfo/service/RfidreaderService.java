package com.wwinfo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wwinfo.model.Rfidreader;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wwinfo.pojo.query.RfidreaderQuery;

/**
 * <p>
 * RFID阅读器表 服务类
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
public interface RfidreaderService extends IService<Rfidreader> {

    /**
     * 分页获取RFID阅读器
     * @param rfidreaderQuery
     * @return
     */
    IPage listPage(RfidreaderQuery rfidreaderQuery);
}
