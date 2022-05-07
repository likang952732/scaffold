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
import com.wwinfo.mapper.AssetMapper;
import com.wwinfo.mapper.InvetoryassetMapper;
import com.wwinfo.mapper.InvetorylackMapper;
import com.wwinfo.model.*;
import com.wwinfo.mapper.InvetorytaskMapper;
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

    @Resource
    private InvetoryassetMapper invetoryassetMapper;

    @Resource
    private AssetMapper assetMapper;

    @Resource
    private InvetorylackMapper invetorylackMapper;

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
        invetorytask.setUserOrgID(curOrgID);
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
        Integer type = invTaskCancelParam.getType();
        Integer status = invetorytask.getStatus();
        if(type == 1 && status == 1){
            throw new BusinessException("任务已经是结束状态");
        } else if(type == 2 && status == 2) {
            throw new BusinessException("任务已经是取消状态");
        }

        if(type == 1) {
            invetorytask.setStatus(1);
            //比对该任务的清单和所属部门的资产
            handle(invetorytask);
        } else if(invTaskCancelParam.getType() == 2){
            invetorytask.setStatus(2);
        }
        invetorytask.setCancelReason(invetorytask.getCancelReason());
        return invetorytaskMapper.updateById(invetorytask);
    }

    @Transactional(rollbackFor = Exception.class)
    private void handle(Invetorytask task){
        QueryWrapper<Invetoryasset> wrapper = new QueryWrapper<>();
        wrapper.eq("taskID", task.getID());
        List<Invetoryasset> inassList = invetoryassetMapper.selectList(wrapper);

        List<Asset> assetList = assetMapper.getListByOrg(task.getUserOrgID());
        Invetorylack lack = null;
        List<Invetorylack> lackList = new ArrayList<>();
        for(Asset as:assetList){
            boolean flag = false;
            for(Invetoryasset ina: inassList){
                 if(as.getID() == ina.getAssetID()){
                     if(as.getRoomID() == ina.getRoomID()){
                         ina.setCheckResult(0);
                     } else if(as.getRoomID() != ina.getRoomID() && ina.getRoomID() != null){
                         ina.setCheckResult(1);
                         ina.setShouldRoomID(ina.getRoomID());
                     } else if(as.getCurStatus() == 1){
                         ina.setCheckResult(2);
                     }
                     flag = true;
                 }
            }
            if(!flag){
                lack = new Invetorylack();
                lack.setTaskID(task.getID());
                lack.setAssetID(as.getID());
                lack.setShouldRoomID(as.getRoomID());
                lack.setResultCheck(0);
                lackList.add(lack);
            }
        }
        invetorylackMapper.addBatch(lackList);
        invetoryassetMapper.updateBatch(inassList);
    }
}
