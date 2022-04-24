package com.wwinfo.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wwinfo.annotation.MyLog;
import com.wwinfo.common.CommonPage;
import com.wwinfo.common.CommonResult;
import com.wwinfo.model.TConfig;
import com.wwinfo.pojo.query.ConfigQuery;
import com.wwinfo.pojo.vo.ConfigAddVO;
import com.wwinfo.pojo.vo.ConfigChgVO;
import com.wwinfo.service.TConfigService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 参数配置
 * </p>
 *
 * @author lk
 * @since 2022-02-17
 */
@Api(value = "参数配置接口", tags = "参数配置接口")
@RestController
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    private TConfigService configService;

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "分页获取参数配置")
    @PostMapping("/list")
    public CommonResult<List<TConfig>> list(ConfigQuery configQuery) {
        return CommonResult.success(configService.listAll(configQuery));
    }


    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "添加参数配置")
    @PostMapping("/add")
    @MyLog(operate = "添加", objectType = "添加参数配置", objectName = "参数配置管理", descript = "添加参数配置: #{#configAddVO.name}")
    public CommonResult add(@Validated ConfigAddVO configAddVO, BindingResult result){
        List<FieldError> fieldErrors = result.getFieldErrors();
        if(!fieldErrors.isEmpty()){
            return CommonResult.failed(fieldErrors.get(0).getDefaultMessage());
        }
        int count = configService.add(configAddVO);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation("编辑参数配置")
    @PostMapping("/update")
    public CommonResult update(@Validated ConfigChgVO configChgVO, BindingResult result) {
        List<FieldError> fieldErrors = result.getFieldErrors();
        if(!fieldErrors.isEmpty()){
            return CommonResult.failed(fieldErrors.get(0).getDefaultMessage());
        }
        int count = configService.update(configChgVO);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

   /* @ApiImplicitParams({@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation("删除参数配置")
    @PostMapping("/delete/{id}")
    public CommonResult delete(@ApiParam(name="id",value="参数配置id",required=true)@PathVariable("id") Long id) {
        int count = configService.delete(id);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }*/

}
