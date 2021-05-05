package com.example.service.impl;

import com.example.model.TOrder;
import com.example.mapper.TOrderMapper;
import com.example.service.TOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author lk
 * @since 2021-05-05
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {

}
