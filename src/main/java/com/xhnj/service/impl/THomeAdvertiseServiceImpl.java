package com.xhnj.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xhnj.common.exception.BusinessException;
import com.xhnj.model.THomeAdvertise;
import com.xhnj.mapper.THomeAdvertiseMapper;
import com.xhnj.service.THomeAdvertiseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 首页轮播广告表 服务实现类
 * </p>
 *
 * @author lk
 * @since 2021-02-24
 */
@Service
public class THomeAdvertiseServiceImpl extends ServiceImpl<THomeAdvertiseMapper, THomeAdvertise> implements THomeAdvertiseService {
    @Resource
    private THomeAdvertiseMapper homeAdvertiseMapper;

    @Override
    public Page listPage(String keyword, Integer pageSize, Integer pageNum) {
        Page<THomeAdvertise> page = new Page<>(pageNum, pageSize);
        QueryWrapper wrapper = new QueryWrapper();
        if(StrUtil.isNotBlank(keyword)) {
            wrapper.like("name", keyword);
        }
        return homeAdvertiseMapper.selectPage(page, wrapper);
    }

    @Override
    public int create(THomeAdvertise homeAdvertise) {
        return homeAdvertiseMapper.insert(homeAdvertise);
    }

    @Override
    public int update(THomeAdvertise homeAdvertise) {
        return homeAdvertiseMapper.updateById(homeAdvertise);
    }

    @Override
    public int delete(Long id) {
        if(id == null)
            throw new BusinessException("id不能为空");
        return homeAdvertiseMapper.deleteById(id);
    }
}
