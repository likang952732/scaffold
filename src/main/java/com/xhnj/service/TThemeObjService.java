package com.xhnj.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xhnj.model.TThemeObj;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 主题分类对象
 * </p>
 *
 * @author lk
 * @since 2021-02-25
 */
public interface TThemeObjService extends IService<TThemeObj> {
    Page listPage(String keyword, Integer pageSize, Integer pageNum);

    int create(TThemeObj themeObj);

    int update(TThemeObj themeObj);

    int delete(Long id);

}
