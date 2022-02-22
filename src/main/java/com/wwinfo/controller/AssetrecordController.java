package com.wwinfo.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wwinfo.common.CommonPage;
import com.wwinfo.common.CommonResult;
import com.wwinfo.pojo.query.AssetrecordQuery;
import com.wwinfo.pojo.res.AssetrecordRes;
import com.wwinfo.service.AssetrecordService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private AssetrecordService assetrecordService;


    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "分页获取资产进出记录")
    @PostMapping("/page")
    public CommonResult<CommonPage<AssetrecordRes>> page(AssetrecordQuery assetrecordQuery) {
        IPage page = assetrecordService.listPage(assetrecordQuery);
        return CommonResult.success(CommonPage.restPage(page));
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "获取资产轨迹")
    @PostMapping("/{assetID}")
    public CommonResult<CommonPage<AssetrecordRes>> trajectory(@ApiParam(name="assetID",value="资产ID",required=true)@PathVariable("assetID")Long assetID,
                                                   @ApiParam(name="sortType",value="排序方式(0->时间倒序;1->时间正序，默认为0)")
                                                   @RequestParam(value =  "sortType", defaultValue = "0")Integer sortType,
                                                         @ApiParam(name="pageSize",value="pageSize(默认10条)")
                                                             @RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize,
                                                         @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        IPage page = assetrecordService.trajectory(assetID, sortType, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));
    }

}
