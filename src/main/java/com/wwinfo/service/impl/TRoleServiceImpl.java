package com.wwinfo.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwinfo.mapper.TRoleMapper;
import com.wwinfo.annotation.MyLog;
import com.wwinfo.common.exception.BusinessException;
import com.wwinfo.mapper.TMenuMapper;
import com.wwinfo.mapper.TRoleMenuMapper;
import com.wwinfo.model.TMenu;
import com.wwinfo.model.TRole;
import com.wwinfo.model.TRoleMenu;
import com.wwinfo.service.TRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author lk
 * @since 2021-02-24
 */
@Service
public class TRoleServiceImpl extends ServiceImpl<TRoleMapper, TRole> implements TRoleService {

    @Resource
    private TRoleMapper roleMapper;

    @Resource
    private TRoleMenuMapper roleMenuMapper;

    @Resource
    private TMenuMapper menuMapper;

    @Override
    public Page listPage(String keyword, Integer pageSize, Integer pageNum) {
        Page<TRole> page = new Page<>(pageNum, pageSize);
        QueryWrapper wrapper = new QueryWrapper();
        if(StrUtil.isNotBlank(keyword)) {
            wrapper.like("name", keyword);
        }
        return roleMapper.selectPage(page, wrapper);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int create(TRole role) {
        return roleMapper.insert(role);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int update(TRole role) {
        return roleMapper.updateById(role);
    }

    @MyLog(operate = "修改", objectType = "系统权限管理", objectName = "角色管理", descript = "删除角色: #{#name}")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delete(Long id, String name) {
        if(id == null)
            throw new BusinessException("id不能为空");
        List<TRole> roleList = roleMapper.getRoleList(id);
        if(roleList.size()>0) {
            throw new BusinessException("用户角色已分配不能删除");
        }
        return roleMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int allocMenu(Long roleId, List<Long> menuIds) {
        roleMenuMapper.deleteByRoleId(roleId);

        List<TRoleMenu> roleMenuList = new ArrayList<>();
        if(menuIds.size()==0)
            throw new BusinessException("分配菜单不能为空");

        if(!CollUtil.isEmpty(menuIds)){
            TRoleMenu roleMenu = null;
            for (Long menuId: menuIds) {
                roleMenu = new TRoleMenu();
                roleMenu.setRoleId(roleId);
                roleMenu.setMenuId(menuId);
                roleMenuList.add(roleMenu);
            }
        }
        return roleMenuMapper.addBatch(roleMenuList);
    }

    @Override
    public List<TRole> getUmsRole(Long adminId) {
        return roleMapper.getUmsRole(adminId);
    }

    @Override
    public List<TMenu> getUmsMenu(Long roleId) {
        return menuMapper.getUmsMenu(roleId);
    }

    @Override
    public List<TMenu> getUmsMenuByAdminId(Long adminId) {
        return menuMapper.getUmsMenuByAdminId(adminId);
    }

    @Override
    public List<TRole> listAll() {
        QueryWrapper<TRole> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 0);
        return roleMapper.selectList(wrapper);
    }

}
