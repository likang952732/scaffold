package com.example.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.model.TChef;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.model.TOrder;

/**
 * <p>
 * 厨师表 服务类
 * </p>
 *
 * @author lk
 * @since 2021-05-05
 */
public interface TChefService extends IService<TChef> {
    Page list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 认证
     * @param chef
     * @return
     */
    int attest(TChef chef);

    /**
     * 接单
     * @param orderId
     * @return
     */
    int receiveOrder(Long orderId);
}
