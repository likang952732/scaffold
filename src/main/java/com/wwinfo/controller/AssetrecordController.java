package com.wwinfo.controller;


import com.wwinfo.common.CommonPage;
import com.wwinfo.common.CommonResult;
import com.wwinfo.pojo.query.AssetrecordQuery;
import com.wwinfo.pojo.res.AssetrecordRes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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


    @ApiOperation(value = "分页获取资产进出记录")
    @GetMapping("/page")
    public CommonResult<CommonPage<AssetrecordRes>> page(AssetrecordQuery assetrecordQuery,
                                                         @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
                                                         @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {

       /* IPage page = logService.listPage(tLog, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));*/
        return null;
    }

    @ApiOperation(value = "获取资产轨迹")
    @GetMapping("/{assetID}")
    public CommonResult<List<AssetrecordRes>> page(@ApiParam(name="assetID",value="资产ID",required=true)@PathVariable("assetID")Long assetID) {

       /* IPage page = logService.listPage(tLog, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));*/
        return null;
    }

}
