package com.wwinfo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwinfo.common.exception.BusinessException;
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
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int add(AssetoutregAddVO assetoutregAddVO) {
        Assetoutreg assetoutreg = BeanUtil.copyProperties(assetoutregAddVO, Assetoutreg.class);
        return assetoutregMapper.insert(assetoutreg);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int chgStatus(ChgStatusParam chgStatusParam) {
        Assetoutreg assetoutreg = BeanUtil.copyProperties(chgStatusParam, Assetoutreg.class);
        return assetoutregMapper.updateById(assetoutreg);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delete(Long id) {
        if(id == null){
            throw new BusinessException("id不能为空");
        }
        return assetoutregMapper.deleteById(id);
    }
}
