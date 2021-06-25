package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.dto.UserOrderParam;
import com.example.model.UserOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lk
 * @since 2021-06-25
 */
public interface UserOrderService extends IService<UserOrder> {
    IPage<UserOrder> listPage(UserOrderParam userOrderParam, Integer pageSize, Integer pageNum);

    int create(UserOrder userOrder);

}
