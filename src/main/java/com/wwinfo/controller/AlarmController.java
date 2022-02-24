package com.wwinfo.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wwinfo.common.CommonPage;
import com.wwinfo.common.CommonResult;
import com.wwinfo.model.Alarm;
import com.wwinfo.pojo.query.AlarmQuery;
import com.wwinfo.service.AlarmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 所有报警记录
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Api(value = "所有报警记录接口", tags = "所有报警记录接口")
@RestController
@RequestMapping("/alarm")
public class AlarmController {

    @Autowired
    private AlarmService alarmService;

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxx')", required = true) })
    @ApiOperation(value = "分页获取所有报警记录")
    @PostMapping("/page")
    public CommonResult<CommonPage<Alarm>> page(AlarmQuery alarmQuery) {
        IPage page = alarmService.listPage(alarmQuery);
        return CommonResult.success(CommonPage.restPage(page));
    }

}
