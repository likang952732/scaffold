package com.wwinfo.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wwinfo.annotation.MyLog;
import com.wwinfo.common.CommonPage;
import com.wwinfo.common.CommonResult;
import com.wwinfo.model.Rfidreader;
import com.wwinfo.pojo.query.RfidreaderQuery;
import com.wwinfo.pojo.vo.RfidreaderAddVO;
import com.wwinfo.pojo.vo.RfidreaderChgParam;
import com.wwinfo.service.RfidreaderService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * RFID阅读器
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Api(value = "RFID阅读器接口", tags = "RFID阅读器接口")
@RestController
@RequestMapping("/rfidreader")
public class RfidreaderController {

    @Autowired
    private RfidreaderService rfidreaderService;

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "分页获取RFID阅读器")
    @PostMapping("/page")
    public CommonResult<CommonPage<Rfidreader>> page(RfidreaderQuery rfidreaderQuery){
        IPage page = rfidreaderService.listPage(rfidreaderQuery);
        return CommonResult.success(CommonPage.restPage(page));
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "添加RFID阅读器")
    @PostMapping("/add")
    @MyLog(operate = "添加", objectType = "添加RFID阅读器", objectName = "添加RFID阅读器", descript = "添加RFID阅读器: #{#rfidreaderAddVO.readerName}")
    public CommonResult add(@Validated RfidreaderAddVO rfidreaderAddVO, BindingResult result){
        List<FieldError> fieldErrors = result.getFieldErrors();
        if(!fieldErrors.isEmpty()){
            return CommonResult.failed(fieldErrors.get(0).getDefaultMessage());
        }
        int count = rfidreaderService.add(rfidreaderAddVO);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation("编辑RFID阅读器")
    @PostMapping("/update")
    @MyLog(operate = "编辑", objectType = "编辑RFID阅读器", objectName = "编辑RFID阅读器", descript = "编辑RFID阅读器: #{#rfidreaderChgParam.readerName}")
    public CommonResult update(@Validated RfidreaderChgParam rfidreaderChgParam, BindingResult result) {
        List<FieldError> fieldErrors = result.getFieldErrors();
        if(!fieldErrors.isEmpty()){
            return CommonResult.failed(fieldErrors.get(0).getDefaultMessage());
        }
        int count = rfidreaderService.update(rfidreaderChgParam);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation("删除RFID阅读器")
    @PostMapping("/delete/{id}")
    @MyLog(operate = "删除", objectType = "删除RFID阅读器", objectName = "删除RFID阅读器", descript = "删除RFID阅读器: #{#id}")
    public CommonResult delete(@ApiParam(name="id",value="RFID阅读器id",required=true)@PathVariable("id") Long id) {
        int count = rfidreaderService.delete(id);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

}
