package com.xhnj.service.impl;

import com.github.pagehelper.PageHelper;
import com.xhnj.dao.UmsAdminRoleRelationDao;
import com.xhnj.dao.UmsRoleDao;
import com.xhnj.mapper.UmsRoleMapper;
import com.xhnj.mapper.UmsRoleMenuRelationMapper;
import com.xhnj.model.*;
import com.xhnj.service.UmsRoleService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/7/30 9:13   
 */
@Service
public class UmsRoleServiceImpl implements UmsRoleService {
    @Resource
    private UmsAdminRoleRelationDao roleRelationDao;
    @Resource
    private UmsRoleDao roleDao;
    @Resource
    private UmsRoleMapper roleMapper;
    @Resource
    private UmsRoleMenuRelationMapper roleMenuRelationMapper;

    @Override
    public List<UmsRole> getUmsRole(Long userId) {
        return roleRelationDao.getRoleList(userId);
    }

    @Override
    public List<UmsMenu> getMenuList(Long adminId) {
        return roleDao.getMenuList(adminId);
    }

    @Override
    public List<UmsRole> list() {
        return roleMapper.selectByExample(new UmsRoleExample());
    }

    @Override
    public List<UmsRole> listPage(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        UmsRoleExample example = new UmsRoleExample();
        UmsRoleExample.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(keyword)) {
            criteria.andNameLike("%" + keyword + "%");
        }
        return roleMapper.selectByExample(example);
    }

    @Override
    public int updateUmsRole(Long roleId, UmsRole umsRole) {
        umsRole.setId(roleId);
        return roleMapper.updateByPrimaryKeySelective(umsRole);
    }

    @Override
    public int create(UmsRole role) {
        return roleMapper.insert(role);
    }

    @Override
    public int delete(Long id) {
        return roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<UmsMenu> listMenu(Long roleId) {
        return roleDao.getMenuListByRoleId(roleId);
    }

    @Override
    public int allocMenu(Long roleId, List<Long> menuIds) {
        int count = menuIds == null ? 0 : menuIds.size();
        UmsRoleMenuRelationExample example = new UmsRoleMenuRelationExample();
        UmsRoleMenuRelationExample.Criteria criteria = example.createCriteria();
        //criteria.andIdEqualTo(roleId);
        criteria.andRoleIdEqualTo(roleId);
        roleMenuRelationMapper.deleteByExample(example);

        if(!CollectionUtils.isEmpty(menuIds)) {
            List<UmsRoleMenuRelation> list = new ArrayList<>();
            for (Long menuId : menuIds) {
                UmsRoleMenuRelation roleMenuRelation = new UmsRoleMenuRelation();
                roleMenuRelation.setRoleId(roleId);
                roleMenuRelation.setMenuId(menuId);
                list.add(roleMenuRelation);
            }
            roleDao.insertList(list);
        }
        return count;
    }

}
