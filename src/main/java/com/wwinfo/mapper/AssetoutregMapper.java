package com.wwinfo.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwinfo.model.Assetoutreg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wwinfo.pojo.query.AssetoutregQuery;
import com.wwinfo.pojo.res.AssetoutregRes;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 资产出库登记表 Mapper 接口
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
public interface AssetoutregMapper extends BaseMapper<Assetoutreg> {

    IPage page(Page<AssetoutregRes> page, @Param("assetoutregQuery") AssetoutregQuery assetoutregQuery);

    Assetoutreg getByAssetID(Long assetID);

}
