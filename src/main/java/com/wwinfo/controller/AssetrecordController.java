package com.wwinfo.controller;


import com.wwinfo.common.CommonPage;
import com.wwinfo.common.CommonResult;
import com.wwinfo.model.Assetrecord;
import com.wwinfo.pojo.query.AssetrecordQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public CommonResult<CommonPage<Assetrecord>> page(AssetrecordQuery assetrecordQuery,
                                                      @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
                                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {

       /* IPage page = logService.listPage(tLog, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));*/
        return null;
    }

}
