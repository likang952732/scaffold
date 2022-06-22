package com.wwinfo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wwinfo.model.TMenu;
import com.wwinfo.model.TRole;
import com.wwinfo.pojo.vo.RoleAddVO;
import com.wwinfo.pojo.vo.RoleChgVO;

import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author lk
 * @since 2021-02-24
 */
public interface TRoleService extends IService<TRole> {

    Page listPage(String keyword, Integer pageSize, Integer pageNum);

    int create(RoleAddVO roleAddVO);

    int update(RoleChgVO roleChgVO);

    /**
     *
     * @param id 角色id
     * @return
     */
    int delete(Long id);

    /**
     *
     * @param roleId 角色id
     * @param menuIds 菜单id, 多个用逗号分隔
     * @return
     */
    int allocMenu(Long roleId, List<Long> menuIds);

    /**
     *
     * @param userId 用户id
     * @return
     */
    List<TRole> getRoleByUserId(Long userId);

    /**
     *
     * @param roleId 角色id
     * @return
     */
    List<TMenu> getUmsMenu(Long roleId);

    /**\
     *
     * @param adminId 后台用户id
     * @return
     */
    List<TMenu> getUmsMenuByAdminId(Long adminId);

    List<TRole> listAll();

}
