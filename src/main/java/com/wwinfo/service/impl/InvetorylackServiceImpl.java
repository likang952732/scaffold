package com.wwinfo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwinfo.model.Invetorylack;
import com.wwinfo.mapper.InvetorylackMapper;
import com.wwinfo.model.Room;
import com.wwinfo.pojo.query.InvetorylackQuery;
import com.wwinfo.pojo.res.InvetorylackRes;
import com.wwinfo.service.InvetorylackService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.web.PortResolverImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 资产盘点缺少表 服务实现类
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Service
public class InvetorylackServiceImpl extends ServiceImpl<InvetorylackMapper, Invetorylack> implements InvetorylackService {

    @Resource
    private InvetorylackMapper invetorylackMapper;

    @Override
    public IPage listPage(InvetorylackQuery invetorylackQuery) {
        Page<InvetorylackRes> page = new Page<>(invetorylackQuery.getPageNum(), invetorylackQuery.getPageSize());
        return invetorylackMapper.page(page, invetorylackQuery);
    }

}
