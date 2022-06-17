package com.wwinfo.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwinfo.model.Assetrecord;
import com.wwinfo.mapper.AssetrecordMapper;
import com.wwinfo.model.Room;
import com.wwinfo.model.User;
import com.wwinfo.pojo.query.AssetrecordQuery;
import com.wwinfo.pojo.res.AssetrecordRes;
import com.wwinfo.service.AssetrecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwinfo.util.UserUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Optional;

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
    public IPage listPage(AssetrecordQuery query) {
        Page<AssetrecordRes> page = new Page<>(query.getPageNum(), query.getPageSize());
        User user = UserUtil.getCurrentUser();
        Optional.ofNullable(user).ifPresent(e -> query.setOrgID(user.getOrgID()));
        return assetrecordMapper.page(page, query);
    }

    @Override
    public IPage trajectory(Long assetID, Integer sortType, Integer pageSize, Integer pageNum) {
        Page<AssetrecordRes> page = new Page<>(pageNum, pageSize);
        if(sortType == null){
            sortType = 0;
        }
        return assetrecordMapper.getByAssetIDAndSortType(page,assetID, sortType);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int add(Assetrecord assetrecord) {
        return assetrecordMapper.insert(assetrecord);
    }
}
