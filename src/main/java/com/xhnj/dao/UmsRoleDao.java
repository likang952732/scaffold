package com.xhnj.dao;

import com.xhnj.model.UmsMenu;
import com.xhnj.model.UmsRoleMenuRelation;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/7/30 9:42   
 */
public interface UmsRoleDao {
    List<UmsMenu> getMenuList(@Param("adminId") Long adminId);

    List<UmsMenu> getMenuListByRoleId(@Param("roleId") Long roleId);

    void insertList(@Param("list") List<UmsRoleMenuRelation> list);

}
