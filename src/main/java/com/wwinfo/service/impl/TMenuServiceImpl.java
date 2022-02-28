package com.wwinfo.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwinfo.annotation.MyLog;
import com.wwinfo.common.exception.BusinessException;
import com.wwinfo.mapper.TMenuMapper;
import com.wwinfo.model.TMenu;
import com.wwinfo.pojo.bo.MenuNode;
import com.wwinfo.pojo.dto.MenuParam;
import com.wwinfo.service.TMenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单表 服务实
 * </p>
 *
 * @author lk
 * @since 2021-02-24
 */
@Service
public class TMenuServiceImpl extends ServiceImpl<TMenuMapper, TMenu> implements TMenuService {

    @Resource
    private TMenuMapper menuMapper;

    @Override
    public Page listPage(MenuParam menuParam, Integer pageSize, Integer pageNum) {
        Page<TMenu> page = new Page<>(pageNum, pageSize);
        QueryWrapper wrapper = new QueryWrapper();
        String keyword = menuParam.getName();
        Long parentId = menuParam.getParentId();
        if(StrUtil.isNotBlank(keyword)) {
            wrapper.like("name", keyword);
        }
        if(parentId != null){
            wrapper.eq("parent_id",parentId);
        }
        return menuMapper.selectPage(page, wrapper);
    }

    @Override
    public Page listLevel(MenuParam menuParam, Integer pageSize, Integer pageNum){
        Page<TMenu> page = new Page<>(pageNum, pageSize);
        QueryWrapper wrapper = new QueryWrapper();
        String keyword = menuParam.getName();
        Long parentId = menuParam.getParentId();
        if(StrUtil.isNotBlank(keyword)) {
            wrapper.like("name", keyword);
        }
        if(parentId != null){
            wrapper.eq("parent_id",parentId);
        }
        wrapper.eq("level",menuParam.getLevel());
        wrapper.orderByDesc("create_time");
        return menuMapper.selectPage(page,wrapper);
    }

    @Override
    public List<MenuNode> treeList() {
            List<TMenu> menuList =  menuMapper.selectList(new QueryWrapper<TMenu>());
        List<MenuNode> result = menuList.stream()
                .filter(menu -> menu.getParentId() == 0)
                .map(menu ->covertMenuNode(menu, menuList))
                .collect(Collectors.toList());
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int create(TMenu menu) {
        if(menu.getParentId() == null)
            menu.setParentId(0L);
        return menuMapper.insert(menu);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int update(TMenu menu) {
        if(menu.getId() == null) {
            throw new BusinessException("id不能为空");
        }
        if(StrUtil.isBlank(menu.getFrontImg())){
            menu.setFrontImg("");
        }
        if(StrUtil.isBlank(menu.getFrontName())){
            menu.setFrontName("");
        }
        return menuMapper.updateById(menu);
    }

    @MyLog(operate = "删除", objectType = "菜单", objectName = "菜单管理", descript = "删除菜单: #{#name}")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delete(Long id, String name) {
        if(id == null)
            throw new BusinessException("id不能为空");
        return menuMapper.deleteById(id);
    }

    private MenuNode covertMenuNode(TMenu menu, List<TMenu> menuList) {
        MenuNode node = new MenuNode();
        BeanUtils.copyProperties(menu, node);
        List<MenuNode> childrens = menuList.stream()
                .filter(subMenu ->node.getId().equals(subMenu.getParentId()))
                .map(subMenu -> covertMenuNode(subMenu, menuList))
                .collect(Collectors.toList());
     /*   if (CollUtil.isNotEmpty(childrens)) {
            node.setChildren(childrens);
        }*/
        node.setChildren(childrens);
        return node;
    }
}
