package com.xhnj.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xhnj.model.TMenu;
import com.xhnj.model.TRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author lk
 * @since 2021-02-24
 */
public interface ITRoleService extends IService<TRole> {
    public Page listPage(String keyword, Integer pageSize, Integer pageNum);

    int create(TRole role);

    int update(TRole role);

    int delete(Long id);

    int allocMenu(Long roleId, List<Long> menuIds);

    List<TRole> getUmsRole(Long adminId);

    List<TMenu> getUmsMenu(Long roleId);

}
