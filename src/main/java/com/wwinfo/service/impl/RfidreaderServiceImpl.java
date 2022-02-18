package com.wwinfo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwinfo.common.ExcludeEmptyQueryWrapper;
import com.wwinfo.model.Rfidreader;
import com.wwinfo.mapper.RfidreaderMapper;
import com.wwinfo.model.TSyslog;
import com.wwinfo.pojo.query.RfidreaderQuery;
import com.wwinfo.service.RfidreaderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * RFID阅读器表 服务实现类
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Service
public class RfidreaderServiceImpl extends ServiceImpl<RfidreaderMapper, Rfidreader> implements RfidreaderService {

    @Resource
    private RfidreaderMapper rfidreaderMapper;

    @Override
    public IPage listPage(RfidreaderQuery rfidreaderQuery) {
        Page<Rfidreader> page = null;
        if(rfidreaderQuery == null) {
            page = new Page<>(1, 10);
            return rfidreaderMapper.selectPage(page, null);
        } else {
            page = new Page<>(rfidreaderQuery.getPageNum(), rfidreaderQuery.getPageSize());
            QueryWrapper<Rfidreader> wrapper = new ExcludeEmptyQueryWrapper<>();
            wrapper.like("readerName", rfidreaderQuery.getReaderName());
            wrapper.eq("interfaceType", rfidreaderQuery.getInterfaceType());
            return rfidreaderMapper.selectPage(page, wrapper);
        }
    }
}
