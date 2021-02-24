package com.xhnj.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xhnj.model.TMenu;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author lk
 * @since 2021-02-24
 */
public interface ITMenuService extends IService<TMenu> {
    Page listPage(String keyword, Integer pageSize, Integer pageNum);

    int create(TMenu menu);

    int update(TMenu menu);

    int delete(Long id);
}
