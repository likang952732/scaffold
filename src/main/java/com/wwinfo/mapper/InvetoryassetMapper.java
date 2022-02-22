package com.wwinfo.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwinfo.model.Invetoryasset;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wwinfo.model.TSyslog;
import com.wwinfo.pojo.query.InvetoryassetQuery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 资产盘点清单表 Mapper 接口
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
public interface InvetoryassetMapper extends BaseMapper<Invetoryasset> {

    IPage page(Page<TSyslog> page, @Param("invetoryassetQuery") InvetoryassetQuery invetoryassetQuery);

}
