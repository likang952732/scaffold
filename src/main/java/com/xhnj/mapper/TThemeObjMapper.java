package com.xhnj.mapper;

import com.xhnj.model.TThemeObj;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 主题分类对象表 Mapper 接口
 * </p>
 *
 * @author lk
 * @since 2021-02-25
 */
public interface TThemeObjMapper extends BaseMapper<TThemeObj> {
    List<TThemeObj> getTThemeObj(@Param("id") Long id, @Param("type")Integer type);

}
