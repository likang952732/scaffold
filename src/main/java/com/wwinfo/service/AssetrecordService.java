package com.wwinfo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wwinfo.model.Assetrecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wwinfo.pojo.query.AssetrecordQuery;

/**
 * <p>
 * 资产进出记录表 服务类
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
public interface AssetrecordService extends IService<Assetrecord> {

    /**
     * 分页获取资产进出记录
     * @param assetrecordQuery
     * @return
     */
    IPage listPage(AssetrecordQuery assetrecordQuery);

    /**
     * 获取资产轨迹
     * @param assetID 资产ID
     * @param sortType 排序方式(0->时间倒序;1->时间正序，默认为0)
     * @param pageSize
     * @param pageNum
     * @return
     */
    IPage trajectory(Long assetID, Integer sortType, Integer pageSize, Integer pageNum);

}
