package com.wwinfo.mapper;

import com.wwinfo.model.Rfidrecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 * RFID阅读记录表 Mapper 接口
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
public interface RfidrecordMapper extends BaseMapper<Rfidrecord> {

    int updateByMap(@Param("map") Map<String, Object> map);

}
