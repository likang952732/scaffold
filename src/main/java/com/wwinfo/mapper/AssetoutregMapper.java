package com.wwinfo.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwinfo.model.Assetoutreg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wwinfo.pojo.query.AssetoutregQuery;
import com.wwinfo.pojo.res.AssetoutregRes;
import com.wwinfo.pojo.vo.AssetoutregAddVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 资产出库登记表 Mapper 接口
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
public interface AssetoutregMapper extends BaseMapper<Assetoutreg> {

    IPage page(Page<AssetoutregRes> page, @Param("query") AssetoutregQuery query);

    Assetoutreg getByAssetID(Long assetID);

    int addBatch(@Param("addVOList") List<AssetoutregAddVO> addVOList);

    IPage page2(Page<AssetoutregRes> page, @Param("query") AssetoutregQuery query);
}
