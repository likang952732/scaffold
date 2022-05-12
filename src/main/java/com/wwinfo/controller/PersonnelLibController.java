package com.wwinfo.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wwinfo.annotation.MyLog;
import com.wwinfo.common.CommonPage;
import com.wwinfo.common.CommonResult;
import com.wwinfo.pojo.query.PersonnelLibQuery;
import com.wwinfo.pojo.res.PersonnelLibRes;
import com.wwinfo.pojo.vo.PersonnelLibAddVO;
import com.wwinfo.pojo.vo.PersonnelLibChgVO;
import com.wwinfo.service.PersonnelLibService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 人员库
 * </p>
 *
 * @author lk
 * @since 2022-04-15
 */
@Api(value = "人员库接口", tags = "人员库接口")
@RestController
@RequestMapping("/personnellib")
public class PersonnelLibController {

    @Autowired
    private PersonnelLibService personnelLibService;

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "分页获取人员库")
    @PostMapping("/page")
    public CommonResult<CommonPage<PersonnelLibRes>> page(PersonnelLibQuery query) {
        IPage page = personnelLibService.listPage(query);
        return CommonResult.success(CommonPage.restPage(page));
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "获取人员库列表")
    @PostMapping("/list")
    public CommonResult<List<PersonnelLibRes>> list(Long orgID) {
        return CommonResult.success(personnelLibService.getList(orgID));
    }


    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "添加人员库")
    @PostMapping("/add")
    @MyLog(operate = "添加", objectType = "添加人员库", objectName = "人员库管理", descript = "添加人员库: #{#addVO.fullName}")
    public CommonResult add(@Validated PersonnelLibAddVO addVO, BindingResult result){
        List<FieldError> fieldErrors = result.getFieldErrors();
        if(!fieldErrors.isEmpty()){
            return CommonResult.failed(fieldErrors.get(0).getDefaultMessage());
        }
        int count = personnelLibService.add(addVO);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }


    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "扫二维码导入")
    @PostMapping("/scancode")
    @MyLog(operate = "扫二维码导入", objectType = "扫二维码导入人员库", objectName = "人员库管理", descript = "扫二维码导入")
    public CommonResult scancode(@Validated @RequestBody List<PersonnelLibAddVO> voList, BindingResult result){
        List<FieldError> fieldErrors = result.getFieldErrors();
        if(!fieldErrors.isEmpty()){
            return CommonResult.failed(fieldErrors.get(0).getDefaultMessage());
        }
        int count = personnelLibService.scancodeImport(voList);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation("编辑人员库")
    @PostMapping("/update")
    @MyLog(operate = "编辑", objectType = "编辑人员库", objectName = "人员库管理", descript = "编辑人员库: #{#chgVO.fullName}")
    public CommonResult update(@Validated PersonnelLibChgVO chgVO, BindingResult result) {
        List<FieldError> fieldErrors = result.getFieldErrors();
        if(!fieldErrors.isEmpty()){
            return CommonResult.failed(fieldErrors.get(0).getDefaultMessage());
        }
        int count = personnelLibService.update(chgVO);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation("删除人员库")
    @PostMapping("/delete/{id}")
    @MyLog(operate = "删除", objectType = "删除人员库", objectName = "人员库管理", descript = "删除人员库: #{id}")
    public CommonResult delete(@ApiParam(name="id",value="人员库id",required=true)@PathVariable("id") Long id) {
        int count = personnelLibService.delete(id);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

}
