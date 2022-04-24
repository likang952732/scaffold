package com.wwinfo.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwinfo.model.Invetoryasset;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wwinfo.pojo.query.InvetoryassetQuery;
import com.wwinfo.pojo.res.InvetoryassetRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 资产盘点清单表 Mapper 接口
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
public interface InvetoryassetMapper extends BaseMapper<Invetoryasset> {

    IPage page(Page<InvetoryassetRes> page, @Param("invetoryassetQuery") InvetoryassetQuery invetoryassetQuery);

    int updateByTaskID(@Param("map")Map<String, Object> map);

    int addBatch(@Param("invastList") List<Invetoryasset> invastList);

    List<Map<String, Object>> getByTaskIdAndAssetIDs(@Param("taskID")Long taskID, @Param("assetIDList") List<Long> assetIDList);

    List<Map<String, Object>> getOverflowByTaskId(@Param("taskID")Long taskID);
}
