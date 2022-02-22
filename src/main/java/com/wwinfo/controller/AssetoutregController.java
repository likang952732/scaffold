package com.wwinfo.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wwinfo.common.CommonPage;
import com.wwinfo.common.CommonResult;
import com.wwinfo.model.Assetoutreg;
import com.wwinfo.pojo.dto.ChgStatusParam;
import com.wwinfo.pojo.query.AssetoutregQuery;
import com.wwinfo.pojo.res.AssetoutregRes;
import com.wwinfo.pojo.vo.AssetoutregAddVO;
import com.wwinfo.service.AssetoutregService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 资产出库登记
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Api(value = "资产出库登记接口", tags = "资产出库登记接口")
@RestController
@RequestMapping("/assetoutreg")
public class AssetoutregController {

    @Autowired
    private AssetoutregService assetoutregService;


    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "分页获取资产出库登记")
    @PostMapping("/page")
    public CommonResult<CommonPage<AssetoutregRes>> page(AssetoutregQuery assetoutregQuery) {
        IPage page = assetoutregService.listPage(assetoutregQuery);
        return CommonResult.success(CommonPage.restPage(page));
    }


    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "资产出库登记")
    @PostMapping("/add")
    public CommonResult add(@Validated AssetoutregAddVO assetoutregAddVO, BindingResult result) {
        List<FieldError> fieldErrors = result.getFieldErrors();
        if(!fieldErrors.isEmpty()){
            return CommonResult.failed(fieldErrors.get(0).getDefaultMessage());
        }
        int count = assetoutregService.add(assetoutregAddVO);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "状态修改")
    @PostMapping("/chgStatus")
    public CommonResult chgStatus(@Validated ChgStatusParam chgStatusParam, BindingResult result) {
        List<FieldError> fieldErrors = result.getFieldErrors();
        if(!fieldErrors.isEmpty()){
            return CommonResult.failed(fieldErrors.get(0).getDefaultMessage());
        }
        int count = assetoutregService.chgStatus(chgStatusParam);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation("资产出库登记删除")
    @PostMapping("/delete/{id}")
    public CommonResult delete(@ApiParam(name="id",value="资产出库登记id",required=true)@PathVariable Long id) {
        int count = assetoutregService.delete(id);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

}
