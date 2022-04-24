package com.wwinfo.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwinfo.model.Entrypos;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wwinfo.pojo.query.EntryposQuery;
import com.wwinfo.pojo.res.EntryposRes;
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

    IPage Page(Page<EntryposRes> page, @Param("entryposQuery") EntryposQuery entryposQuery);

    List<EntryposRes> getList(@Param("entryposQuery")EntryposQuery entryposQuery);

    int chgById(@Param("entrypos")Entrypos entrypos);
}
