package com.wwinfo.mapper;

import com.wwinfo.model.TConfig;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 * 系统配置表 Mapper 接口
 * </p>
 *
 * @author lk
 * @since 2022-02-17
 */
public interface TConfigMapper extends BaseMapper<TConfig> {

    TConfig getConfigByMap(@Param("map") Map<String, Object> map);

    TConfig getConfigByfieldName(@Param("fieldName") String fieldName);
}
