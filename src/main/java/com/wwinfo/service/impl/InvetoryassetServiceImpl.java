package com.wwinfo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwinfo.common.ExcludeEmptyQueryWrapper;
import com.wwinfo.model.Invetoryasset;
import com.wwinfo.mapper.InvetoryassetMapper;
import com.wwinfo.model.TSyslog;
import com.wwinfo.pojo.query.InvetoryassetQuery;
import com.wwinfo.service.InvetoryassetService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 资产盘点清单表 服务实现类
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Service
public class InvetoryassetServiceImpl extends ServiceImpl<InvetoryassetMapper, Invetoryasset> implements InvetoryassetService {

    @Resource
    private InvetoryassetMapper invetoryassetMapper;

    @Override
    public IPage listPage(InvetoryassetQuery invetoryassetQuery) {
        Page<TSyslog> page = null;
        if(invetoryassetQuery == null) {
            page = new Page<>(1, 10);
        } else {
            page = new Page<>(invetoryassetQuery.getPageNum(), invetoryassetQuery.getPageSize());
        }
        return null;
    }
}
