package com.wwinfo.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwinfo.model.Invetorytask;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wwinfo.pojo.query.InvetorytaskQuery;
import com.wwinfo.pojo.res.InvetorytaskRes;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 资产盘点任务表 Mapper 接口
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
public interface InvetorytaskMapper extends BaseMapper<Invetorytask> {

    IPage page(Page<InvetorytaskRes> page, @Param("invetorytaskQuery") InvetorytaskQuery invetorytaskQuery);

}
