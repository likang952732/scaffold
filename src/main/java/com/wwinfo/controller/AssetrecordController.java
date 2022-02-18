package com.wwinfo.controller;


import com.wwinfo.common.CommonPage;
import com.wwinfo.common.CommonResult;
import com.wwinfo.pojo.query.AssetrecordQuery;
import com.wwinfo.pojo.res.AssetrecordRes;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 资产进出记录
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Api(value = "资产进出记录接口", tags = "资产进出记录接口")
@RestController
@RequestMapping("/assetrecord")
public class AssetrecordController {


    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "分页获取资产进出记录")
    @PostMapping("/page")
    public CommonResult<CommonPage<AssetrecordRes>> page(AssetrecordQuery assetrecordQuery) {

       /* IPage page = logService.listPage(tLog, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));*/
        return null;
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "获取资产轨迹")
    @PostMapping("/{assetID}")
    public CommonResult<List<AssetrecordRes>> page(@ApiParam(name="assetID",value="资产ID",required=true)@PathVariable("assetID")Long assetID) {

       /* IPage page = logService.listPage(tLog, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));*/
        return null;
    }

}
