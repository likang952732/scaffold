package com.wwinfo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wwinfo.common.ExcludeEmptyQueryWrapper;
import com.wwinfo.common.exception.BusinessException;
import com.wwinfo.model.TConfig;
import com.wwinfo.mapper.TConfigMapper;
import com.wwinfo.pojo.query.ConfigQuery;
import com.wwinfo.pojo.vo.ConfigAddVO;
import com.wwinfo.pojo.vo.ConfigChgVO;
import com.wwinfo.service.TConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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

    @Value("${config.fields}")
    private String configFields;

    @Override
    public  List<TConfig> listAll(ConfigQuery configQuery) {
        QueryWrapper<TConfig> wrapper = new ExcludeEmptyQueryWrapper<>();
        wrapper.like(StrUtil.isNotBlank(configQuery.getName()), "name", configQuery.getName());
        return configMapper.selectList(wrapper);
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
        if (existConfig != null) {
            throw new BusinessException("参数名称或参数键名不能重复");
        }
        TConfig config = BeanUtil.copyProperties(configAddVO, TConfig.class);
        return configMapper.insert(config);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int update(ConfigChgVO configChgVO) {
        List<String> configFieldList = Arrays.asList(configFields.split(","));
        String fieldName = configChgVO.getFieldName();
        if(configFieldList.contains(fieldName)){
            if(!configChgVO.getValue().matches("[1-9]*")){
                throw new BusinessException("该参数值只能是不为0的整数");
            }
        }
        TConfig config = BeanUtil.copyProperties(configChgVO, TConfig.class);
        return configMapper.updateById(config);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delete(Long id) {
        if (id == null) {
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
