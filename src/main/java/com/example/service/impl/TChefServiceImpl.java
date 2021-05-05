package com.example.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mapper.TAdminMapper;
import com.example.mapper.TOrderMapper;
import com.example.model.TAdmin;
import com.example.model.TChef;
import com.example.mapper.TChefMapper;
import com.example.model.TOrder;
import com.example.service.TChefService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.util.UserUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 * 厨师表 服务实现类
 * </p>
 *
 * @author lk
 * @since 2021-05-05
 */
@Service
public class TChefServiceImpl extends ServiceImpl<TChefMapper, TChef> implements TChefService {
    @Resource
    private TChefMapper chefMapper;
    @Resource
    private TAdminMapper adminMapper;
    @Resource
    private TOrderMapper orderMapper;

    @Override
    public Page list(String keyword, Integer pageSize, Integer pageNum) {
        TAdmin admin = UserUtil.getCurrentAdminUser();
        Page<TChef> page = new Page<>(pageNum, pageSize);
        QueryWrapper wrapper = new QueryWrapper();
        if(StrUtil.isNotBlank(keyword)) {
            wrapper.like("name", keyword);
        }
        wrapper.eq("admin_id", admin.getId());
        return chefMapper.selectPage(page, wrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int attest(TChef chef) {
        TAdmin admin = UserUtil.getCurrentAdminUser();
        chef.setAdminId(admin.getId());
        chefMapper.insert(chef);
        //更新当前用户类型
        admin.setUserType(1);
        return adminMapper.updateById(admin);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int receiveOrder(Long orderId) {
        TAdmin admin = UserUtil.getCurrentAdminUser();
        TOrder order = orderMapper.selectById(orderId);
        order.setAdminId(admin.getId());
        order.setReceiveTime(new Date());
        return orderMapper.insert(order);
    }
}
