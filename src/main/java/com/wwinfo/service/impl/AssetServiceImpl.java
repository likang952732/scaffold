package com.wwinfo.service.impl;

import com.wwinfo.model.Asset;
import com.wwinfo.mapper.AssetMapper;
import com.wwinfo.service.AssetService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 资产表 服务实现类
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Service
public class AssetServiceImpl extends ServiceImpl<AssetMapper, Asset> implements AssetService {

}
