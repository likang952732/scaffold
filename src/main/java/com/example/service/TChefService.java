package com.example.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dto.ChefParam;
import com.example.model.TChef;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.model.TOrder;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <p>
 * 厨师表 服务类
 * </p>
 *
 * @author lk
 * @since 2021-05-05
 */
public interface TChefService extends IService<TChef> {
    Page list(ChefParam chefParam, Integer pageSize, Integer pageNum);

    TChef info(Long id);

    /**
     * 认证
     * @param chef
     * @return
     */
    int attest(TChef chef);

    int add(TChef chef);

    /**
     * 接单
     * @param orderId
     * @return
     */
    int receiveOrder(Long orderId);
}
