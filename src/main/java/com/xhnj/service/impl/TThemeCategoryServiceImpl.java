package com.xhnj.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xhnj.common.exception.BusinessException;
import com.xhnj.mapper.TThemeObjMapper;
import com.xhnj.model.TThemeCategory;
import com.xhnj.mapper.TThemeCategoryMapper;
import com.xhnj.model.TThemeObj;
import com.xhnj.service.TThemeCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 主题分类表 服务实现类
 * </p>
 *
 * @author lk
 * @since 2021-02-25
 */
@Service
public class TThemeCategoryServiceImpl extends ServiceImpl<TThemeCategoryMapper, TThemeCategory> implements TThemeCategoryService {
    @Resource
    private TThemeCategoryMapper themeCategoryMapper;

    @Resource
    private TThemeObjMapper themeObjMapper;

    @Override
    public Page listPage(String keyword, Integer pageSize, Integer pageNum) {
        Page<TThemeCategory> page = new Page<>(pageNum, pageSize);
        QueryWrapper wrapper = new QueryWrapper();
        if(StrUtil.isNotBlank(keyword)) {
            wrapper.like("name", keyword);
        }
        return themeCategoryMapper.selectPage(page, wrapper);
    }

    @Override
    public List<TThemeObj> getTThemeObj(Long id, Integer type) {
        return themeObjMapper.getTThemeObj(id, type);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int create(TThemeCategory themeCategory) {
        return themeCategoryMapper.insert(themeCategory);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int update(TThemeCategory themeCategory) {
        return themeCategoryMapper.updateById(themeCategory);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delete(Long id) {
        if(id == null)
            throw new BusinessException("id不能为空");
        return 0;
    }
}
