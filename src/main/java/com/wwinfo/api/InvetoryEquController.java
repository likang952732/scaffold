package com.wwinfo.api;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.wwinfo.common.ApiCommonResult;
import com.wwinfo.service.InvetoryEquService;
import com.wwinfo.util.BusinUtil;
import com.wwinfo.util.RequestUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description: api
 *
 * @Author: lk
 * DateTime: 2022-02-24 21:30
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class InvetoryEquController {

    @Autowired
    private InvetoryEquService invetoryEquService;

    @Autowired
    private BusinUtil businUtil;

    @ApiOperation(value = "获取库房列表")
    @RequestMapping("/rooms")
    public ApiCommonResult list(HttpServletRequest request) throws Exception {
        String checkResult = checkRequest(request);
        if(StrUtil.isNotBlank(checkResult)) {
            return ApiCommonResult.failed(checkResult, new JSONObject(), new JSONArray());
        }
        return ApiCommonResult.success(new JSONObject(), invetoryEquService.list());
    }

    @ApiOperation(value = "获取当前需要盘点的部门列表")
    @RequestMapping("/orgs")
    public ApiCommonResult orgs(HttpServletRequest request) throws Exception{
        String checkResult = checkRequest(request);
        if(StrUtil.isNotBlank(checkResult)) {
            return ApiCommonResult.failed(checkResult, new JSONObject(), new JSONArray());
        }
        return ApiCommonResult.success(new JSONObject(),invetoryEquService.getOrgs());
    }

    @ApiOperation(value = "获取当前需要盘点的资产列表")
    @RequestMapping("/assets")
    public ApiCommonResult getAssetList(HttpServletRequest request, String orgIDs) throws Exception {
        String checkResult = checkRequest(request);
        if(StrUtil.isNotBlank(checkResult)) {
            return ApiCommonResult.failed(checkResult, new JSONObject(), new JSONArray());
        }
        return ApiCommonResult.success(new JSONObject(), invetoryEquService.getAssetList(orgIDs));
    }

    @ApiOperation(value = "上传盘点信息")
    @RequestMapping("/invetories")
    public ApiCommonResult invetories(HttpServletRequest request, String machineID, Long roomID, String IDs) throws Exception{
        String checkResult = checkRequest(request);
        if(StrUtil.isNotBlank(checkResult)) {
            return ApiCommonResult.failed(checkResult, new JSONObject(), new JSONArray());
        }
        invetoryEquService.uploadInvetorieInfo(machineID, roomID, IDs);
        return ApiCommonResult.success(new JSONObject(), new JSONArray());
    }

    private String checkRequest(HttpServletRequest request) throws Exception{
        Map<String, Object> requestParam = RequestUtil.analysisRequestParam(request);
        log.info("InvetoryEquController中{}接收到的参数: {}", request.getServletPath(), requestParam.toString());
        return businUtil.checkRequest(request, requestParam);
    }

}
