package com.example.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dto.UserOrderParam;
import com.example.model.UserOrder;
import com.example.mapper.UserOrderMapper;
import com.example.service.UserOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lk
 * @since 2021-06-25
 */
@Service
public class UserOrderServiceImpl extends ServiceImpl<UserOrderMapper, UserOrder> implements UserOrderService {
    @Resource
    private UserOrderMapper userOrderMapper;

    @Override
    public IPage<UserOrder> listPage(UserOrderParam userOrderParam, Integer pageSize, Integer pageNum) {
        IPage<UserOrder> page = new Page<>(pageNum, pageSize);
        return userOrderMapper.listPage(page,userOrderParam);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int create(UserOrder userOrder) {
        userOrder.setStatus(0);
        return userOrderMapper.insert(userOrder);
    }
}
