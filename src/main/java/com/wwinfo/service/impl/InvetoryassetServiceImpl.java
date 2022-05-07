package com.wwinfo.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwinfo.common.exception.BusinessException;
import com.wwinfo.constant.SysConstant;
import com.wwinfo.mapper.AssetMapper;
import com.wwinfo.mapper.InvetorytaskMapper;
import com.wwinfo.model.Invetoryasset;
import com.wwinfo.mapper.InvetoryassetMapper;
import com.wwinfo.model.Invetorytask;
import com.wwinfo.pojo.query.InvetoryassetQuery;
import com.wwinfo.pojo.res.InvetoryassetRes;
import com.wwinfo.service.InvetoryassetService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwinfo.util.UserUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 资产盘点清单表 服务实现类
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Service
public class InvetoryassetServiceImpl extends ServiceImpl<InvetoryassetMapper, Invetoryasset> implements InvetoryassetService {

    @Resource
    private InvetoryassetMapper invetoryassetMapper;

    @Resource
    private InvetorytaskMapper invetorytaskMapper;

    @Resource
    private AssetMapper assetMapper;

    @Override
    public IPage listPage(InvetoryassetQuery invetoryassetQuery) {
        Page<InvetoryassetRes> page = new Page<>(invetoryassetQuery.getPageNum(), invetoryassetQuery.getPageSize());
        invetoryassetQuery.setOrgID(UserUtil.getCurrentUser().getOrgID());
        Integer type = invetoryassetQuery.getType();
        IPage result = new Page();
        if((type!= null && type == 0) || type == null){
            result = invetoryassetMapper.page(page, invetoryassetQuery);
        } else if(invetoryassetQuery.getType() != null && invetoryassetQuery.getType() == 1){
            invetoryassetQuery.setCheckResults("0, 1, 2");
            result = invetoryassetMapper.page(page, invetoryassetQuery);
        }
        List<InvetoryassetRes> records = result.getRecords();
        for(InvetoryassetRes res: records){
            if(res.getCurStatus() == 1){
                res.setRoomName("外部");
            }
        }
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int confirm(Long id, String assetIDs) {
        if(id == null){
            throw new BusinessException("任务id不能为空");
        }

        if(StrUtil.isBlank(assetIDs)){
            throw new BusinessException("assetIDs参数不能为空");
        }

        Invetorytask invetorytask = invetorytaskMapper.selectById(id);
        if(invetorytask.getStatus() != SysConstant.INVETORY_TASK_STATUS_END) {
            throw new BusinessException("只有结束状态的盘点任务才能确认");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("taskID", id);
        map.put("resultCheck", SysConstant.RESULTCHECK_CONFIRM);
        invetoryassetMapper.updateByTaskID(map);

        //更新资产的roomID
        List<Long> assetIDList = Arrays.asList(assetIDs.split(",")).stream().map(Long::valueOf).collect(Collectors.toList());
        List<Map<String, Object>> maplist = invetoryassetMapper.getByTaskIdAndAssetIDs(id, assetIDList);
        if(CollUtil.isNotEmpty(maplist)){
            assetMapper.updateBatchMap(maplist);
        }
        return 1;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int addBatch(List<Invetoryasset> invastList) {
        return invetoryassetMapper.addBatch(invastList);
    }

}
