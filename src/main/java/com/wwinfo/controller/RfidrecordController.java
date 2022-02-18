package com.wwinfo.controller;


import com.wwinfo.common.CommonPage;
import com.wwinfo.common.CommonResult;
import com.wwinfo.model.Rfidrecord;
import com.wwinfo.pojo.query.RfidrecordQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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


    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "分页获取RFID阅读记录")
    @PostMapping("/page")
    public CommonResult<CommonPage<Rfidrecord>> page(RfidrecordQuery rfidrecordQuery) {

       /* IPage page = logService.listPage(tLog, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));*/
        return null;
    }

}
