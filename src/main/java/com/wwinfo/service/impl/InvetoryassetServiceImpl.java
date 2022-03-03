package com.wwinfo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwinfo.common.exception.BusinessException;
import com.wwinfo.constant.SysConstant;
import com.wwinfo.mapper.InvetorytaskMapper;
import com.wwinfo.model.Invetoryasset;
import com.wwinfo.mapper.InvetoryassetMapper;
import com.wwinfo.model.Invetorytask;
import com.wwinfo.pojo.query.InvetoryassetQuery;
import com.wwinfo.pojo.res.InvetoryassetRes;
import com.wwinfo.service.InvetoryassetService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

    @Override
    public IPage listPage(InvetoryassetQuery invetoryassetQuery) {
        Page<InvetoryassetRes> page = new Page<>(invetoryassetQuery.getPageNum(), invetoryassetQuery.getPageSize());
        return invetoryassetMapper.page(page, invetoryassetQuery);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int confirm(Long id, String assetIDs) {
        Invetorytask invetorytask = invetorytaskMapper.selectById(id);
        if(invetorytask.getStatus() != SysConstant.INVETORY_TASK_STATUS_END)
            throw new BusinessException("只有结束状态的盘点任务才能确认");

        Map<String, Object> map = new HashMap<>();
        map.put("taskID", id);
        map.put("resultCheck", SysConstant.RESULTCHECK_CONFIRM);
        invetoryassetMapper.updateByTaskID(map);

        //更新资产的room
        List<Long> assetIDList = Arrays.asList(assetIDs.split(",")).stream().map(Long::valueOf).collect(Collectors.toList());
        invetorytaskMapper.getByTaskIDAndAssetIDs(id, assetIDList);
        //TODO

        return 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int addBatch(List<Invetoryasset> invastList) {
        return invetoryassetMapper.addBatch(invastList);
    }

}
