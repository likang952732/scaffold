package com.wwinfo.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wwinfo.common.CommonPage;
import com.wwinfo.common.CommonResult;
import com.wwinfo.pojo.query.RfidrecordQuery;
import com.wwinfo.pojo.res.RfidrecordRes;
import com.wwinfo.service.RfidrecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * RFID阅读记录
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Api(value = "RFID阅读记录接口", tags = "RFID阅读记录接口")
@RestController
@RequestMapping("/rfidrecord")
public class RfidrecordController {

    @Autowired
    private RfidrecordService rfidrecordService;

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "分页获取RFID阅读记录")
    @PostMapping("/page")
    public CommonResult<CommonPage<RfidrecordRes>> page(RfidrecordQuery rfidrecordQuery) {
        IPage page = rfidrecordService.listPage(rfidrecordQuery);
        return CommonResult.success(CommonPage.restPage(page));
    }

}
