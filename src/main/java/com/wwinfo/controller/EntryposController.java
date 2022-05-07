package com.wwinfo.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wwinfo.annotation.MyLog;
import com.wwinfo.common.CommonPage;
import com.wwinfo.common.CommonResult;
import com.wwinfo.pojo.query.EntryposQuery;
import com.wwinfo.pojo.res.EntryposRes;
import com.wwinfo.pojo.vo.EntryposAddVO;
import com.wwinfo.pojo.vo.EntryposChgVO;
import com.wwinfo.service.EntryposService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * RFID进出口设置
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Api(value = "RFID进出口设置接口", tags = "RFID进出口设置接口")
@RestController
@RequestMapping("/entrypos")
public class EntryposController {

    @Autowired
    private EntryposService entryposService;

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "分页获取RFID进出口设置")
    @PostMapping("/page")
    public CommonResult<CommonPage<EntryposRes>> page(EntryposQuery entryposQuery) {
        IPage page = entryposService.listPage(entryposQuery);
        return CommonResult.success(CommonPage.restPage(page));
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "获取RFID进出口设置列表")
    @PostMapping("/list")
    public CommonResult<List<EntryposRes>> list(EntryposQuery entryposQuery) {
        return CommonResult.success(entryposService.list(entryposQuery));
    }

  /*  @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "获取详情")
    @PostMapping("/detail/{id}")
    public CommonResult detail(@ApiParam(name="id",value="RFID进出口设置id",required=true)@PathVariable("id") Long id) {
        return CommonResult.success(entryposService.getDetail(id));
    }*/


    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "添加RFID进出口设置")
    @PostMapping("/add")
    @MyLog(operate = "添加", objectType = "添加RFID进出口设置", objectName = "RFID进出口设置管理", descript = "添加RFID进出口设置: #{#admin.username}")
    public CommonResult add(@Validated EntryposAddVO entryposAddVO, BindingResult result){
        List<FieldError> fieldErrors = result.getFieldErrors();
        if(!fieldErrors.isEmpty()){
            return CommonResult.failed(fieldErrors.get(0).getDefaultMessage());
        }
        int count = entryposService.add(entryposAddVO);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation("编辑RFID进出口设置")
    @PostMapping("/update")
    public CommonResult update(@Validated EntryposChgVO entryposChgVO, BindingResult result) {
        List<FieldError> fieldErrors = result.getFieldErrors();
        if(!fieldErrors.isEmpty()){
            return CommonResult.failed(fieldErrors.get(0).getDefaultMessage());
        }
        int count = entryposService.update(entryposChgVO);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation("删除RFID进出口设置")
    @PostMapping("/delete/{id}")
    public CommonResult delete(@ApiParam(name="id",value="RFID进出口设置id",required=true)@PathVariable("id") Long id) {
        int count = entryposService.delete(id);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }


}
