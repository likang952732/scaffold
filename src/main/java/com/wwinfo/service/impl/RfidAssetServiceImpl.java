package com.wwinfo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwinfo.mapper.OrganizeMapper;
import com.wwinfo.mapper.RfidAssetMapper;
import com.wwinfo.model.Asset;
import com.wwinfo.model.Organize;
import com.wwinfo.model.RfidAsset;
import com.wwinfo.service.OrganizeService;
import com.wwinfo.service.RfidAssetService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-04-21 23:25
 */
@Service
public class RfidAssetServiceImpl extends ServiceImpl<RfidAssetMapper, RfidAsset> implements RfidAssetService {

    @Resource
    private RfidAssetMapper rfidAssetMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int addBatch(List<RfidAsset> list, int count) {
        int insertLength = list.size();
        int i = 0;
        List<RfidAsset> partialList = null;
        while (insertLength > count){
            partialList = list.subList(i, i+count);
            rfidAssetMapper.addBatch(partialList);
            i = i + count;
            insertLength = insertLength - count;
        }
        if(insertLength > 0){
            partialList = list.subList(i, i+insertLength);
            rfidAssetMapper.addBatch(partialList);
        }
        return 1;
    }
}
