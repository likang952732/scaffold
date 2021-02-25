package com.xhnj.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xhnj.common.exception.BusinessException;
import com.xhnj.model.TThemeObj;
import com.xhnj.mapper.TThemeObjMapper;
import com.xhnj.service.TThemeObjService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 主题分类对象表 服务实现类
 * </p>
 *
 * @author lk
 * @since 2021-02-25
 */
@Service
public class TThemeObjServiceImpl extends ServiceImpl<TThemeObjMapper, TThemeObj> implements TThemeObjService {
    @Resource
    private TThemeObjMapper themeObjMapper;

    @Override
    public Page listPage(String keyword, Integer pageSize, Integer pageNum) {
        Page<TThemeObj> page = new Page<>(pageNum, pageSize);
        QueryWrapper wrapper = new QueryWrapper();
        if(StrUtil.isNotBlank(keyword)) {
            wrapper.like("name", keyword);
        }
        return themeObjMapper.selectPage(page, wrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int create(TThemeObj themeObj) {
        return themeObjMapper.insert(themeObj);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int update(TThemeObj themeObj) {
        return themeObjMapper.updateById(themeObj);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delete(Long id) {
        if(id == null)
            throw new BusinessException("id不能为空");
        return themeObjMapper.deleteById(id);
    }
}
