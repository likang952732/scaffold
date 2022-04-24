package com.wwinfo.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwinfo.model.Invetorytask;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wwinfo.pojo.query.InvetorytaskQuery;
import com.wwinfo.pojo.res.AssetApiRes;
import com.wwinfo.pojo.res.InvetorytaskRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 资产盘点任务表 Mapper 接口
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
public interface InvetorytaskMapper extends BaseMapper<Invetorytask> {

    IPage page(Page<InvetorytaskRes> page, @Param("query") InvetorytaskQuery query);

    List<InvetorytaskRes> getTaskByParam(@Param("map")Map<String, Object> map);

    List<AssetApiRes> getAssetByTask(@Param("map")Map<String, Object> map);

    List<String> getRFIDIds(@Param("assetId")Long assetId);

    List<InvetorytaskRes> getByTaskIDAndAssetIDs(Long id, List<Long> assetIDList);

    List<Invetorytask> getTaskByOrgIds(@Param("orgIDList") List<Long> orgIDList);

    List<Invetorytask> getTaskByAssetID(@Param("assetID") Long assetID);

    List<Map<String, Object>> getTaskByRoomId(Long roomID);

}
