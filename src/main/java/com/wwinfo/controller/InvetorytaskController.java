package com.wwinfo.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wwinfo.annotation.MyLog;
import com.wwinfo.common.CommonPage;
import com.wwinfo.common.CommonResult;
import com.wwinfo.pojo.dto.InvTaskCancelParam;
import com.wwinfo.pojo.query.InvetorytaskQuery;
import com.wwinfo.pojo.res.InvetorytaskRes;
import com.wwinfo.pojo.vo.InvetorytaskAddVO;
import com.wwinfo.pojo.vo.InvetorytaskChgVO;
import com.wwinfo.service.InvetorytaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 资产盘点任务
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Api(value = "资产盘点任务接口", tags = "资产盘点任务接口")
@RestController
@RequestMapping("/invetorytask")
public class InvetorytaskController {

    @Autowired
    private InvetorytaskService invetorytaskService;

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "分页获取资产盘点任务")
    @PostMapping("/page")
    public CommonResult<CommonPage<InvetorytaskRes>> page(InvetorytaskQuery invetorytaskQuery) {
        IPage page = invetorytaskService.listPage(invetorytaskQuery);
        return CommonResult.success(CommonPage.restPage(page));
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "添加资产盘点任务")
    @PostMapping("/add")
    @MyLog(operate = "添加", objectType = "添加资产盘点任务", objectName = "添加资产盘点任务", descript = "添加资产盘点任务")
    public CommonResult add(@Validated InvetorytaskAddVO invetorytaskAddVO, BindingResult result){
        List<FieldError> fieldErrors = result.getFieldErrors();
        if(!fieldErrors.isEmpty()){
            return CommonResult.failed(fieldErrors.get(0).getDefaultMessage());
        }
        int count = invetorytaskService.add(invetorytaskAddVO);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "编辑资产盘点任务")
    @PostMapping("/update")
    @MyLog(operate = "编辑", objectType = "编辑资产盘点任务", objectName = "编辑资产盘点任务", descript = "编辑资产盘点任务: #{#invetorytaskChgVO.id}")
    public CommonResult update(@Validated InvetorytaskChgVO invetorytaskChgVO, BindingResult result){
        List<FieldError> fieldErrors = result.getFieldErrors();
        if(!fieldErrors.isEmpty()){
            return CommonResult.failed(fieldErrors.get(0).getDefaultMessage());
        }
        int count = invetorytaskService.update(invetorytaskChgVO);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }


    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation("结束或取消")
    @PostMapping("/cancel")
    @MyLog(operate = "取消", objectType = "盘点任务结束或取消", objectName = "盘点任务取消", descript = "盘点任务取消: #{#invTaskCancelParam.id}")
    public CommonResult cancel(@Validated InvTaskCancelParam invTaskCancelParam, BindingResult result) {
        List<FieldError> fieldErrors = result.getFieldErrors();
        if(!fieldErrors.isEmpty()){
            return CommonResult.failed(fieldErrors.get(0).getDefaultMessage());
        }
        int count = invetorytaskService.cancel(invTaskCancelParam);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

}
