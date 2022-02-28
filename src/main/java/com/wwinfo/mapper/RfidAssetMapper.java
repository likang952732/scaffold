package com.wwinfo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wwinfo.model.RfidAsset;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-02-28 14:54
 */
public interface RfidAssetMapper extends BaseMapper<RfidAsset> {

    List<RfidAsset> getByAssetID(@Param("assetID")Long assetID);

    int addBatch(@Param("mapList")List<Map<String, Object>> mapList);

    int updateBatchStatus(List<Long> idList, Map<String, Object> map);

}
