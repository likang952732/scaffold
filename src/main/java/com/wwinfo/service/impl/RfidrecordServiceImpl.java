package com.wwinfo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwinfo.common.ExcludeEmptyQueryWrapper;
import com.wwinfo.model.Rfidrecord;
import com.wwinfo.mapper.RfidrecordMapper;
import com.wwinfo.pojo.query.RfidrecordQuery;
import com.wwinfo.service.RfidrecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * RFID阅读记录表 服务实现类
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Service
public class RfidrecordServiceImpl extends ServiceImpl<RfidrecordMapper, Rfidrecord> implements RfidrecordService {

    @Resource
    private RfidrecordMapper rfidrecordMapper;

    @Override
    public IPage
    listPage(RfidrecordQuery rfidrecordQuery) {
        Page<Rfidrecord> page = new Page<>(rfidrecordQuery.getPageNum(), rfidrecordQuery.getPageSize());
        QueryWrapper<Rfidrecord> wrapper = new ExcludeEmptyQueryWrapper<>();
        wrapper.eq("status", rfidrecordQuery.getStatus());
        wrapper.eq("isOther", rfidrecordQuery.getIsOther());
        return rfidrecordMapper.selectPage(page, wrapper);
    }

    @Override
    public List<Rfidrecord> getByrFidNo(String cardNo) {
        QueryWrapper<Rfidrecord> wrapper = new ExcludeEmptyQueryWrapper<>();
        wrapper.eq("rfidNo", cardNo);
        return rfidrecordMapper.selectList(wrapper);
    }

    @Override
    public List<Rfidrecord> getByrAssetID(Long assetID) {
        QueryWrapper<Rfidrecord> wrapper = new ExcludeEmptyQueryWrapper<>();
        wrapper.eq("assetID", assetID);
        return rfidrecordMapper.selectList(wrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int add(Rfidrecord rfidrecord) {
        return rfidrecordMapper.insert(rfidrecord);
    }

}
