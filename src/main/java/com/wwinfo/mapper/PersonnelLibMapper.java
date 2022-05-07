package com.wwinfo.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwinfo.model.PersonnelLib;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wwinfo.pojo.query.PersonnelLibQuery;
import com.wwinfo.pojo.res.PersonnelLibRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 人员库表 Mapper 接口
 * </p>
 *
 * @author lk
 * @since 2022-04-15
 */
public interface PersonnelLibMapper extends BaseMapper<PersonnelLib> {

    IPage listPage(Page<PersonnelLibRes> page, @Param("query") PersonnelLibQuery query);

    List<PersonnelLibRes> getList(@Param("orgID") Long orgID);

    int batchAdd(@Param("voList")List<PersonnelLib> voList);

}
