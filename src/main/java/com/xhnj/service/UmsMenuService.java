package com.xhnj.service;
import com.xhnj.dto.UmsMenuNode;
import com.xhnj.model.UmsMenu;

import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/7/30 14:52   
 */
public interface UmsMenuService {
    List<UmsMenu> list(Long parentId, String keyword, Integer pageSize, Integer pageNum);

    int create(UmsMenu menu);

    int delete(Long menuId);

    int update(Long menuId, UmsMenu menu);

    UmsMenu getMenuById(Long menuId);

    List<UmsMenuNode> treeList();
}
