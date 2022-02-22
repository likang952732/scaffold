package com.wwinfo.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwinfo.model.Assetrecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wwinfo.pojo.query.AssetrecordQuery;
import com.wwinfo.pojo.res.AssetrecordRes;

/**
 * <p>
 * 资产进出记录表 Mapper 接口
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
public interface AssetrecordMapper extends BaseMapper<Assetrecord> {

    IPage page(Page<AssetrecordRes> page, AssetrecordQuery assetrecordQuery);

    IPage getByAssetIDAndSortType(Long assetID, Integer sortType);

}
