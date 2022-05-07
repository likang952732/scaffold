package com.wwinfo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwinfo.common.exception.BusinessException;
import com.wwinfo.constant.SysConstant;
import com.wwinfo.mapper.AssetMapper;
import com.wwinfo.mapper.AssetrecordMapper;
import com.wwinfo.model.Asset;
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
import java.util.*;
import java.util.stream.Collectors;

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

    @Resource
    private AssetMapper assetMapper;

    @Override
    public IPage listPage(AssetoutregQuery assetoutregQuery) {
        User user = UserUtil.getCurrentUser();
        assetoutregQuery.setOrgID(user.getOrgID());
        Page<AssetoutregRes> page = new Page<>(assetoutregQuery.getPageNum(), assetoutregQuery.getPageSize());
        if(assetoutregQuery.getStatus() == null){
            return assetoutregMapper.page2(page, assetoutregQuery);
        }
        return assetoutregMapper.page(page, assetoutregQuery);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int addBatch(AssetoutregAddVO addVO) {
        List<Long> idList = Arrays.asList(addVO.getAssetIDs().split(",")).stream().map(Long::valueOf).collect(Collectors.toList());
        if(addVO.getOutType() == 1 && (StrUtil.isBlank(addVO.getUsages()) || StrUtil.isBlank(addVO.getBorrowOrg())
                || StrUtil.isBlank(addVO.getBorrowMan()))){
            throw new BusinessException("借用出库时,用途,借用人,借用部门不能为空");
        }
        List<Asset> assets = assetMapper.selectBatchIds(idList);
        for(Asset asset: assets){
            if(asset.getIsBand() == 0){
                throw new BusinessException("只有绑定过RFID的才可以出库");
            }
            if(asset.getCurStatus() == 1){
                throw new BusinessException("已是外部状态的资产不能再出库");
            }
        }

        List<AssetoutregAddVO> list = new ArrayList<>();
        for(Long assetId: idList){
            AssetoutregAddVO vo = BeanUtil.copyProperties(addVO, AssetoutregAddVO.class);
            vo.setAssetID(assetId);
            vo.setRegTime(new Date());
            if(StrUtil.isBlank(vo.getEstimateTime())){
                vo.setEstimateTime(null);
            }
            list.add(vo);
        }
        assetoutregMapper.addBatch(list);
        //更新curStatus为1
        assetMapper.updateStatusBatchIds(idList, 1);
        return 1;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int chg(ChgStatusParam chgStatusParam) {
        Assetoutreg assetoutreg = BeanUtil.copyProperties(chgStatusParam, Assetoutreg.class);
        assetoutreg.setID(chgStatusParam.getId());
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

        if(assetoutreg.getStatus() != 0){
            throw new BusinessException("只有状态为未出库的才能删除");
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
