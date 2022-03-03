package com.wwinfo.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwinfo.model.Assetrecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wwinfo.pojo.query.AssetrecordQuery;
import com.wwinfo.pojo.res.AssetrecordRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 资产进出记录表 Mapper 接口
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
public interface AssetrecordMapper extends BaseMapper<Assetrecord> {

    IPage page(Page<AssetrecordRes> page, @Param("assetrecordQuery") AssetrecordQuery assetrecordQuery);

    IPage getByAssetIDAndSortType(Page<AssetrecordRes> page, @Param("assetID")Long assetID, @Param("sortType")Integer sortType);

    List<Assetrecord> getByMap(@Param("map") Map<String, Object> map);

}
