package com.wwinfo.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwinfo.model.AbnoReadRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wwinfo.pojo.query.AbnoReadRecordQuery;
import com.wwinfo.pojo.res.AbnoReadRecordRes;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 异常读取记录表 Mapper 接口
 * </p>
 *
 * @author lk
 * @since 2022-05-03
 */
public interface AbnoReadRecordMapper extends BaseMapper<AbnoReadRecord> {

    IPage listPage(Page<AbnoReadRecordRes> page, @Param("query") AbnoReadRecordQuery query);
}
