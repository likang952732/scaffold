package com.wwinfo.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwinfo.model.Asset;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wwinfo.pojo.bo.AssetStatusExcel;
import com.wwinfo.pojo.query.AssetQuery;
import com.wwinfo.pojo.res.AssetApiRes;
import com.wwinfo.pojo.res.AssetRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 资产表 Mapper 接口
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
public interface AssetMapper extends BaseMapper<Asset> {

    IPage page(Page<AssetRes> page, @Param("assetQuery") AssetQuery assetQuery);

    int updateBatchByParam(@Param("idList") List<Long> idList, @Param("map")Map<String, Object> map);

    List<AssetStatusExcel> getExportListByParam(@Param("map")Map<String, Object> map);

    List<Asset> checkRepeat(@Param("assetList") List<Asset> assetList);

    void addBatch(@Param("assetList")List<Asset> assetList);

    List<AssetApiRes> getAssetListByOrgs(@Param("orgs") List<Long> orgs);

    List<AssetRes> getAssetListByIDs(@Param("ids") List<Long> ids);

    List<String> getRFIDIds(@Param("assetId")Long assetId);

}
