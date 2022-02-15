package com.wwinfo.controller;


import com.wwinfo.common.CommonPage;
import com.wwinfo.common.CommonResult;
import com.wwinfo.model.TSyslog;
import com.wwinfo.pojo.dto.ChgStatusParam;
import com.wwinfo.pojo.query.AssetoutregQuery;
import com.wwinfo.pojo.query.SysLogQuery;
import com.wwinfo.pojo.res.AssetoutregRes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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


    @ApiOperation(value = "分页获取资产出库登记")
    @GetMapping("/page")
    public CommonResult<CommonPage<AssetoutregRes>> page(AssetoutregQuery assetoutregQuery,
                                                         @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
                                                         @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {

       /* IPage page = logService.listPage(tLog, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));*/
        return null;
    }



    @ApiOperation(value = "状态修改")
    @PutMapping("/chgStatus")
    public CommonResult chgStatus(@RequestBody ChgStatusParam chgStatusParam, BindingResult result) {

        return null;
    }

}
