package com.wwinfo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wwinfo.model.Invetoryasset;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wwinfo.pojo.query.InvetoryassetQuery;

/**
 * <p>
 * 资产盘点清单表 服务类
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
public interface InvetoryassetService extends IService<Invetoryasset> {

    /**
     * 分页获取资产盘点清单
     * @param invetoryassetQuery
     * @return
     */
    IPage listPage(InvetoryassetQuery invetoryassetQuery);

    /**
     *
     * @param id 任务id
     * @param assetIDs 需要确认的资产id(逗号分隔)
     * @return
     */
    int confirm(Long id, String assetIDs);

}
