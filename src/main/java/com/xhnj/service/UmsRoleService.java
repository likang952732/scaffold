package com.xhnj.service;

import com.xhnj.model.UmsMenu;
import com.xhnj.model.UmsRole;

import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/7/30 9:02   
 */
public interface UmsRoleService {
    List<UmsRole> getUmsRole(Long adminId);

    List<UmsMenu> getMenuList(Long adminId);

    List<UmsRole> list();

    List<UmsRole> listPage(String keyword, Integer pageSize, Integer pageNum);

    int updateUmsRole(Long roleId, UmsRole umsRole);

    int create(UmsRole role);

    int delete(Long id);

    List<UmsMenu> listMenu(Long roleId);

    int allocMenu(Long roleId, List<Long> menuIds);

}
