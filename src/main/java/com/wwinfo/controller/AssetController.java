package com.wwinfo.controller;


import com.wwinfo.common.CommonPage;
import com.wwinfo.common.CommonResult;
import com.wwinfo.model.TSyslog;
import com.wwinfo.pojo.query.AssetQuery;
import com.wwinfo.pojo.query.SysLogQuery;
import com.wwinfo.pojo.res.AssetRes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 资产
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Api(value = "资产接口", tags = "资产接口")
@RestController
@RequestMapping("/asset")
public class AssetController {


    @ApiOperation(value = "分页获取资产")
    @GetMapping("/page")
    public CommonResult<CommonPage<AssetRes>> page(AssetQuery assetQuery,
                                                   @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {

       /* IPage page = logService.listPage(tLog, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));*/
        return null;
    }

}
