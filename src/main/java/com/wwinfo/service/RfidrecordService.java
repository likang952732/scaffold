package com.wwinfo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wwinfo.model.Rfidrecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wwinfo.pojo.query.RfidrecordQuery;

import java.util.List;
import java.util.Map;

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

    /**
     *
     * @param rfidNo
     * @return
     */
    List<Rfidrecord> getByrFidNo(String rfidNo);

    /**
     *
     * @param assetID
     * @return
     */
    List<Rfidrecord> getByrAssetID(Long assetID);

    int add(Rfidrecord rfidrecord);

    int updateByMap(Map<String, Object> hmData);

}
