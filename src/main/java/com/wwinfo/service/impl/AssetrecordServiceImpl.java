package com.wwinfo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwinfo.model.Assetrecord;
import com.wwinfo.mapper.AssetrecordMapper;
import com.wwinfo.model.Room;
import com.wwinfo.pojo.query.AssetrecordQuery;
import com.wwinfo.pojo.res.AssetrecordRes;
import com.wwinfo.service.AssetrecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 资产进出记录表 服务实现类
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Service
public class AssetrecordServiceImpl extends ServiceImpl<AssetrecordMapper, Assetrecord> implements AssetrecordService {

    @Resource
    private AssetrecordMapper assetrecordMapper;

    @Override
    public IPage listPage(AssetrecordQuery assetrecordQuery) {
        Page<AssetrecordRes> page = new Page<>(assetrecordQuery.getPageNum(), assetrecordQuery.getPageSize());
        return assetrecordMapper.page(page, assetrecordQuery);
    }

    @Override
    public IPage trajectory(Long assetID, Integer sortType, Integer pageSize, Integer pageNum) {
        Page<AssetrecordRes> page = new Page<>(pageNum, pageSize);
        if(sortType == null){
            sortType = 0;
        }
        return assetrecordMapper.getByAssetIDAndSortType(page,assetID, sortType);
    }
}
