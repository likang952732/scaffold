package com.wwinfo.mapper;

import com.wwinfo.model.Entrypos;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * RFID进出口设置表 Mapper 接口
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
public interface EntryposMapper extends BaseMapper<Entrypos> {

    List<HashMap<String, Object>> getByParam(@Param("map") Map<String, Object> map);

}
