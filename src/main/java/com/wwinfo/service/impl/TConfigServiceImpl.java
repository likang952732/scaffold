package com.wwinfo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwinfo.common.ExcludeEmptyQueryWrapper;
import com.wwinfo.common.exception.BusinessException;
import com.wwinfo.model.TConfig;
import com.wwinfo.mapper.TConfigMapper;
import com.wwinfo.pojo.query.ConfigQuery;
import com.wwinfo.pojo.vo.ConfigAddVO;
import com.wwinfo.pojo.vo.ConfigChgVO;
import com.wwinfo.service.TConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 系统配置表 服务实现类
 * </p>
 *
 * @author lk
 * @since 2022-02-17
 */
@Service
public class TConfigServiceImpl extends ServiceImpl<TConfigMapper, TConfig> implements TConfigService {

    @Resource
    private TConfigMapper configMapper;

    @Override
    public IPage listPage(ConfigQuery configQuery) {
        Page<TConfig> page = new Page<>(configQuery.getPageNum(), configQuery.getPageSize());
        QueryWrapper<TConfig> wrapper = new ExcludeEmptyQueryWrapper<>();
        wrapper.like("name", configQuery.getName());
        return configMapper.selectPage(page, wrapper);
    }

    @Override
    public TConfig getConfig(String fieldName) {
        Map<String, Object> map = new HashMap<>();
        map.put("fieldName", fieldName);
        return configMapper.getConfigByMap(map);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int add(ConfigAddVO configAddVO) {
        TConfig existConfig = getConfig(configAddVO.getName(), configAddVO.getFieldName());
        if(existConfig != null){
            throw new BusinessException("参数名称或参数键名不能重复");
        }
        TConfig config = BeanUtil.copyProperties(configAddVO, TConfig.class);
        return configMapper.insert(config);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int update(ConfigChgVO configChgVO) {
        QueryWrapper<TConfig> wrapper = new ExcludeEmptyQueryWrapper<>();
        wrapper.eq("name", configChgVO.getName());
        TConfig existConfig = configMapper.selectOne(wrapper);
        if(existConfig != null && configChgVO.getId() != existConfig.getId()){
            throw new BusinessException("参数名称不能重复");
        }
        TConfig config = BeanUtil.copyProperties(configChgVO, TConfig.class);
        return configMapper.updateById(config);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delete(Long id) {
        if(id == null){
            throw new BusinessException("id不能为空");
        }
        return configMapper.deleteById(id);
    }

    private TConfig getConfig(String name, String fieldName) {
        QueryWrapper<TConfig> wrapper = new ExcludeEmptyQueryWrapper<>();
        wrapper.eq("name", name)
                .or()
                .eq("fieldName", fieldName);
        return configMapper.selectOne(wrapper);
    }
}
