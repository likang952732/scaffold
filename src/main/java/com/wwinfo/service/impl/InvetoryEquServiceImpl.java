package com.wwinfo.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wwinfo.common.ExcludeEmptyQueryWrapper;
import com.wwinfo.common.exception.BusinessException;
import com.wwinfo.mapper.AssetMapper;
import com.wwinfo.mapper.InvetoryassetMapper;
import com.wwinfo.mapper.InvetorytaskMapper;
import com.wwinfo.mapper.RoomMapper;
import com.wwinfo.model.Invetoryasset;
import com.wwinfo.model.Room;
import com.wwinfo.pojo.res.AssetApiRes;
import com.wwinfo.pojo.res.InvetorytaskRes;
import com.wwinfo.service.InvetoryEquService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-02-25 17:01
 */
@Service
public class InvetoryEquServiceImpl implements InvetoryEquService {

    @Resource
    private RoomMapper roomMapper;

    @Resource
    private InvetorytaskMapper invetorytaskMapper;

    @Resource
    private AssetMapper assetMapper;

    @Resource
    private InvetoryassetMapper invetoryassetMapper;

    @Override
    public JSONArray list() {
        JSONArray array = new JSONArray();
        QueryWrapper<Room> wrapper = new ExcludeEmptyQueryWrapper<>();
        List<Room> roomList = roomMapper.selectList(wrapper);
        if(CollUtil.isNotEmpty(roomList)){
            JSONObject json = null;
            for(Room m: roomList){
                json = new JSONObject();
                json.put("ID", m.getID());
                json.put("roomName", m.getRoomName());
                json.put("address", m.getAddress());
                json.put("desc", m.getDescription());
                json.put("remark", m.getRemark());
                array.add(json);
            }
        }
        return array;
    }

    @Override
    public JSONArray getOrgs() {
        JSONArray array = new JSONArray();
        Map<String, Object> map = new HashMap<>();
        map.put("status", 0);
        List<InvetorytaskRes> taskList = invetorytaskMapper.getTaskByParam(map);
        if(CollUtil.isNotEmpty(taskList)){
            JSONObject json = null;
            for(InvetorytaskRes res: taskList){
                json = new JSONObject();
                json.put("orgID", res.getOrgID());
                json.put("orgName", res.getOrgName());
                json.put("remark", res.getRemark());
                array.add(json);
            }
        }
        return array;
    }

    @Override
    public JSONArray getAssetList(String orgIDs) {
        JSONArray array = new JSONArray();
        if(StrUtil.isNotBlank(orgIDs)){
            List<Long> orgList = Arrays.asList(orgIDs.split(",")).stream().map(Long::valueOf).collect(Collectors.toList());
            List<AssetApiRes> resList = assetMapper.getAssetListByOrgs(orgList);
            handleResult(array, resList);
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("status", 0);
            List<AssetApiRes> resList = invetorytaskMapper.getAssetByTask(map);
            handleResult(array, resList);
        }
        return array;
    }

    private void handleResult(JSONArray array, List<AssetApiRes> resList){
        if(CollUtil.isNotEmpty(resList)){
            JSONObject json = null;
            for(AssetApiRes res: resList) {
                json = new JSONObject();
                json = new JSONObject();
                json.put("ID", res.getID());
                json.put("name", res.getName());
                json.put("assetNo", res.getAssetNo());
                json.put("brand", res.getBrand());
                json.put("model", res.getModel());
                json.put("orgID", res.getOrgID());
                json.put("orgName", res.getOrgName());
                json.put("remark", res.getRemark());
                if(CollUtil.isNotEmpty(res.getRfids())){
                    json.put("rfids", res.getRfids().stream().map(String::valueOf).collect(Collectors.joining(",")));
                }
                array.add(json);
            }
        }
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void uploadInvetorieInfo(String machineID, Long roomID, String iDs) {
        if(StrUtil.isBlank(machineID) || roomID == null || StrUtil.isBlank(iDs)){
            throw new BusinessException("业务参数不能为空");
        }

        QueryWrapper<Invetoryasset> wrapper = new ExcludeEmptyQueryWrapper<>();
        wrapper.eq("machineID", machineID);

    }

}
