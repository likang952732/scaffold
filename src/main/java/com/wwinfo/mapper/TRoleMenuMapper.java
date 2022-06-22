package com.wwinfo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wwinfo.model.TRoleMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色菜单表 Mapper 接口
 * </p>
 *
 * @author lk
 * @since 2021-02-24
 */
public interface TRoleMenuMapper extends BaseMapper<TRoleMenu> {
    int addBatch(@Param("roleMenuList") List<TRoleMenu> roleMenuList);

    int deleteByRoleId(@Param("roleId") Long roleId);

    List<TRoleMenu> queryMuneByRoleId(@Param("roleId") Long roleId);
}
