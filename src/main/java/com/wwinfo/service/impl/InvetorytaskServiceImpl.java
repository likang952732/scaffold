package com.wwinfo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
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
import com.wwinfo.model.User;
import com.wwinfo.pojo.dto.InvTaskCancelParam;
import com.wwinfo.pojo.query.InvetorytaskQuery;
import com.wwinfo.pojo.res.InvetorytaskRes;
import com.wwinfo.pojo.vo.InvetorytaskAddVO;
import com.wwinfo.pojo.vo.InvetorytaskChgVO;
import com.wwinfo.service.InvetorytaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwinfo.util.UserUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

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
        User user = UserUtil.getCurrentUser();
        Optional.ofNullable(user).ifPresent(e -> invetorytaskQuery.setOrgID(user.getOrgID()));
        IPage result = invetorytaskMapper.page(page, invetorytaskQuery);
        List<InvetorytaskRes> records = result.getRecords();
        JSONObject json = null;
        JSONArray array = new JSONArray();
        for(InvetorytaskRes res: records){
            List<String> itemList = Arrays.asList(res.getOrgIDName().split(","));
            for(String str: itemList){
                json = new JSONObject();
                json.put("orgID", str.substring(str.indexOf("-")+1, str.length()));
                json.put("orgName", str.substring(0, str.indexOf("-")));
                array.add(json);
            }
            res.setOrgArray(array);
        }
        result.setRecords(records);
        return result;
    }

    @Override
    public List<Invetorytask> getByAssetID(Long assetID) {
        return invetorytaskMapper.getTaskByAssetID(assetID);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int add(InvetorytaskAddVO addVO) {
        //  部门和它下面的子部门， 只能存在一个盘点中的任务
        Long curOrgID = UserUtil.getCurrentUser().getOrgID();
        QueryWrapper<Invetorytask> wrapper = new QueryWrapper<>();
        wrapper.eq("userOrgID", curOrgID);
        wrapper.eq("status", 0);
        Invetorytask existTask = invetorytaskMapper.selectOne(wrapper);
        if(existTask != null){
            throw new BusinessException("一个部门只能存在一个盘点中的任务");
        }
        Invetorytask invetorytask = BeanUtil.copyProperties(addVO, Invetorytask.class);
        invetorytask.setOrgID(addVO.getOrgIDs());
        return invetorytaskMapper.insert(invetorytask);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int update(InvetorytaskChgVO chgVO) {
        Invetorytask invetorytask = BeanUtil.copyProperties(chgVO, Invetorytask.class);
        invetorytask.setID(chgVO.getId());
        invetorytask.setOrgID(chgVO.getOrgIDs());
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
