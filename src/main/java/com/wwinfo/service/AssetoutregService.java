package com.wwinfo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wwinfo.model.Assetoutreg;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wwinfo.pojo.dto.ChgStatusParam;
import com.wwinfo.pojo.query.AssetoutregQuery;
import com.wwinfo.pojo.vo.AssetoutregAddVO;

import java.util.List;

/**
 * <p>
 * 资产出库登记表 服务类
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
public interface AssetoutregService extends IService<Assetoutreg> {

    /**
     * 分页获取资产出库登记
     * @param assetoutregQuery
     * @return
     */
    IPage listPage(AssetoutregQuery assetoutregQuery);

    /**
     * 资产出库登记
     * @param
     * @return
     */
    int addBatch(AssetoutregAddVO addVO);

    /**
     * 状态修改
     * @param chgStatusParam
     * @return
     */
    int chg(ChgStatusParam chgStatusParam);

    /**
     * 资产出库登记删除
     * @param id
     * @return
     */
    int delete(Long id);

}
