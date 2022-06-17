package com.wwinfo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wwinfo.model.RfidMapping;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-02-28 14:40
 */
public interface RfidMappingMapper extends BaseMapper<RfidMapping> {

    List<RfidMapping> getByPrintNos(@Param("list") List<String> rfidPrintNoList);

    List<RfidMapping> getByAssetID(@Param("assetID")Long assetID);

    void updateBatch(@Param("list") List<String> list, @Param("status") Integer status);
}
