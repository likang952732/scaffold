package com.xhnj.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xhnj.model.TThemeCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xhnj.model.TThemeObj;

import java.util.List;

/**
 * <p>
 * 主题分类
 * </p>
 *
 * @author lk
 * @since 2021-02-25
 */
public interface TThemeCategoryService extends IService<TThemeCategory> {
    Page listPage(String keyword, Integer pageSize, Integer pageNum);

    List<TThemeObj> getTThemeObj(Long id, Integer type);

    int create(TThemeCategory themeCategory);

    int update(TThemeCategory themeCategory);

    int delete(Long id);
}
