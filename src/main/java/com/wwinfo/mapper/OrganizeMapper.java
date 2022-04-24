package com.wwinfo.mapper;

import com.wwinfo.model.Organize;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 部门表 Mapper 接口
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
public interface OrganizeMapper extends BaseMapper<Organize> {

    int deleteById(@Param("orgID")Long orgID);

    List<Organize> getUpTreeByChild(@Param("orgID")Long orgID);

    List<Organize> getChildByOrgID(@Param("orgID")Long orgID);

    List<Organize> getOrgByUserId(@Param("orgID")Long orgID);
}
