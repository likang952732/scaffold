package com.wwinfo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwinfo.model.Assetoutreg;
import com.wwinfo.mapper.AssetoutregMapper;
import com.wwinfo.model.Room;
import com.wwinfo.pojo.dto.ChgStatusParam;
import com.wwinfo.pojo.query.AssetoutregQuery;
import com.wwinfo.pojo.res.AssetoutregRes;
import com.wwinfo.pojo.vo.AssetoutregAddVO;
import com.wwinfo.service.AssetoutregService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 资产出库登记表 服务实现类
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Service
public class AssetoutregServiceImpl extends ServiceImpl<AssetoutregMapper, Assetoutreg> implements AssetoutregService {

    @Resource
    private AssetoutregMapper assetoutregMapper;

    @Override
    public IPage listPage(AssetoutregQuery assetoutregQuery) {
        Page<AssetoutregRes> page = new Page<>(assetoutregQuery.getPageNum(), assetoutregQuery.getPageSize());
        return assetoutregMapper.page(page, assetoutregQuery);
    }

    @Override
    public int add(AssetoutregAddVO assetoutregAddVO) {
        return 0;
    }

    @Override
    public int chgStatus(ChgStatusParam chgStatusParam) {
        return 0;
    }

    @Override
    public int delete(Long id) {
        return 0;
    }
}
