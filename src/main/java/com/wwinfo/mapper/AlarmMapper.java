package com.wwinfo.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwinfo.model.Alarm;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wwinfo.pojo.query.AlarmQuery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 所有报警记录表 Mapper 接口
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
public interface AlarmMapper extends BaseMapper<Alarm> {

    IPage getPage(Page<Alarm> page, @Param("query")AlarmQuery alarmQuery);
}
