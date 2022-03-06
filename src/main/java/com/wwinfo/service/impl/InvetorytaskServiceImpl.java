package com.wwinfo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwinfo.common.ExcludeEmptyQueryWrapper;
import com.wwinfo.common.exception.BusinessException;
import com.wwinfo.constant.SysConstant;
import com.wwinfo.model.Asset;
import com.wwinfo.model.Invetorytask;
import com.wwinfo.mapper.InvetorytaskMapper;
import com.wwinfo.model.Room;
import com.wwinfo.pojo.dto.InvTaskCancelParam;
import com.wwinfo.pojo.query.InvetorytaskQuery;
import com.wwinfo.pojo.res.InvetorytaskRes;
import com.wwinfo.pojo.vo.InvetorytaskAddVO;
import com.wwinfo.pojo.vo.InvetorytaskChgVO;
import com.wwinfo.service.InvetorytaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 资产盘点任务表 服务实现类
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Service
public class InvetorytaskServiceImpl extends ServiceImpl<InvetorytaskMapper, Invetorytask> implements InvetorytaskService {

    @Resource
    private InvetorytaskMapper invetorytaskMapper;

    @Override
    public IPage listPage(InvetorytaskQuery invetorytaskQuery) {
        Page<InvetorytaskRes> page = new Page<>(invetorytaskQuery.getPageNum(), invetorytaskQuery.getPageSize());
        return invetorytaskMapper.page(page, invetorytaskQuery);
    }

    @Override
    public List<Invetorytask> getByAssetID(Long assetID) {
        return invetorytaskMapper.getTaskByAssetID(assetID);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int add(InvetorytaskAddVO invetorytaskAddVO) {
        Map<String, Object> map = new HashMap<>();
        map.put("orgID", invetorytaskAddVO.getOrgID());
        map.put("status", SysConstant.INVETORY_TASK_STATUS_PROCESS);
        List<InvetorytaskRes> list = invetorytaskMapper.getTaskByParam(map);
        if(CollUtil.isNotEmpty(list)){
            throw new BusinessException("同一个部门只能存在一个盘点中的任务");
        }
        Invetorytask invetorytask = BeanUtil.copyProperties(invetorytaskAddVO, Invetorytask.class);
        return invetorytaskMapper.insert(invetorytask);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int update(InvetorytaskChgVO invetorytaskChgVO) {
        Invetorytask invetorytask = BeanUtil.copyProperties(invetorytaskChgVO, Invetorytask.class);
        invetorytask.setID(invetorytaskChgVO.getId());
        return invetorytaskMapper.updateById(invetorytask);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int cancel(InvTaskCancelParam invTaskCancelParam) {
        Invetorytask invetorytask = invetorytaskMapper.selectById(invTaskCancelParam.getId());
        invetorytask.setStatus(1);
        if(invTaskCancelParam.getType() == 2){
            invetorytask.setStatus(2);
        }
        invetorytask.setCancelReason(invetorytask.getCancelReason());
        return invetorytaskMapper.updateById(invetorytask);
    }
}
