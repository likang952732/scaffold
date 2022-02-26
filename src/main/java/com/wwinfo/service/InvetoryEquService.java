package com.wwinfo.service;

import cn.hutool.json.JSONArray;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-02-25 16:55
 */
public interface InvetoryEquService {

    /**
     * 获取库房列表
     * @return
     */
    JSONArray list();

    /**
     *获取当前需要盘点的部门列表
     * @return
     */
    JSONArray getOrgs();

    /**
     * 获取当前需要盘点的资产列表
     * @param orgIDs
     * @return
     */
    JSONArray getAssetList(String orgIDs);

    /**
     * 上传盘点信息
     * @param machineID
     * @param roomID
     * @param iDs
     * @return
     */
    void uploadInvetorieInfo(String machineID, Long roomID, String iDs);

}
