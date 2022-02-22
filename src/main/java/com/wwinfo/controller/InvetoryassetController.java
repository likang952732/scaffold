package com.wwinfo.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wwinfo.annotation.MyLog;
import com.wwinfo.common.CommonPage;
import com.wwinfo.common.CommonResult;
import com.wwinfo.pojo.query.InvetoryassetQuery;
import com.wwinfo.pojo.res.InvetoryassetRes;
import com.wwinfo.service.InvetoryassetService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 资产盘点清单
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Api(value = "资产盘点清单接口", tags = "资产盘点清单接口")
@RestController
@RequestMapping("/invetoryasset")
public class InvetoryassetController {

    @Autowired
    private InvetoryassetService invetoryassetService;

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "分页获取资产盘点清单")
    @PostMapping("/page")
    public CommonResult<CommonPage<InvetoryassetRes>> page(InvetoryassetQuery invetoryassetQuery) {
        IPage page = invetoryassetService.listPage(invetoryassetQuery);
        return CommonResult.success(CommonPage.restPage(page));
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation("盘点结果损益确认")
    @PostMapping("/confirm")
    @MyLog(operate = "修改", objectType = "资产盘点", objectName = "盘点结果损益确认", descript = "盘点结果损益确认")
    public CommonResult confirm(@ApiParam(name="id",value="任务id",required=true)Long id,
                                @ApiParam(name="assetIDs",value="需要确认的资产id(逗号分隔)",required=true)String assetIDs) {
        int count = invetoryassetService.confirm(id, assetIDs);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

}
