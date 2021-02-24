package com.xhnj.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xhnj.model.THomeAdvertise;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 首页轮播广告表 服务类
 * </p>
 *
 * @author lk
 * @since 2021-02-24
 */
public interface THomeAdvertiseService extends IService<THomeAdvertise> {
    Page listPage(String keyword, Integer pageSize, Integer pageNum);

    int create(THomeAdvertise homeAdvertise);

    int update(THomeAdvertise homeAdvertise);

    int delete(Long id);

}
