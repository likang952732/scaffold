package com.wwinfo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wwinfo.model.Rfidreader;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wwinfo.pojo.query.RfidreaderQuery;
import com.wwinfo.pojo.vo.RfidreaderAddVO;
import com.wwinfo.pojo.vo.RfidreaderChgParam;

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

    /**
     * 添加RFID阅读器
     * @param rfidreaderAddVO
     * @return
     */
    int add(RfidreaderAddVO rfidreaderAddVO);

    /**
     * 编辑RFID阅读器
     * @param rfidreaderChgParam
     * @return
     */
    int update(RfidreaderChgParam rfidreaderChgParam);

    /**
     * 删除RFID阅读器
     * @param id
     * @return
     */
    int delete(Long id);

    Rfidreader getByReaderName(String readerName);

}
