package com.wwinfo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwinfo.common.exception.BusinessException;
import com.wwinfo.constant.SysConstant;
import com.wwinfo.mapper.AssetrecordMapper;
import com.wwinfo.model.Assetoutreg;
import com.wwinfo.mapper.AssetoutregMapper;
import com.wwinfo.model.Assetrecord;
import com.wwinfo.model.User;
import com.wwinfo.pojo.dto.ChgStatusParam;
import com.wwinfo.pojo.query.AssetoutregQuery;
import com.wwinfo.pojo.res.AssetoutregRes;
import com.wwinfo.pojo.vo.AssetoutregAddVO;
import com.wwinfo.service.AssetoutregService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwinfo.util.UserUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Resource
    private AssetrecordMapper assetrecordMapper;

    @Override
    public IPage listPage(AssetoutregQuery assetoutregQuery) {
        User user = UserUtil.getCurrentUser();
        assetoutregQuery.setOrgID(user.getOrgID());
        Page<AssetoutregRes> page = new Page<>(assetoutregQuery.getPageNum(), assetoutregQuery.getPageSize());
        return assetoutregMapper.page(page, assetoutregQuery);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int add(AssetoutregAddVO assetoutregAddVO) {
        //判断outType
        if(assetoutregAddVO.getOutType() == 1 && (StrUtil.isBlank(assetoutregAddVO.getUsages()) || StrUtil.isBlank(assetoutregAddVO.getBorrowOrg())
                                                  || StrUtil.isBlank(assetoutregAddVO.getBorrowMan()))){
            throw new BusinessException("借用出库时,usage,borrowMan,borrowOrg不能为空");
        }
        Assetoutreg assetoutreg = BeanUtil.copyProperties(assetoutregAddVO, Assetoutreg.class);
        assetoutreg.setStatus(SysConstant.ASSET_OUTREG_STATUS);
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
        Assetoutreg assetoutreg = assetoutregMapper.selectById(id);
        if(assetoutreg == null){
            throw new BusinessException("未查询到资产登记记录");
        }
        //未检测到出库的才允许删除
        Map<String, Object> map = new HashMap<>();
        map.put("assetID", assetoutreg.getAssetID());
        List<Assetrecord> list = assetrecordMapper.getByMap(map);
        if(CollUtil.isNotEmpty(list)){
            throw new BusinessException("该资产已检测到出库，不能删除");
        }
        return assetoutregMapper.deleteById(id);
    }
}
