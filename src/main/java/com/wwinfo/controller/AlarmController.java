package com.wwinfo.controller;


import com.wwinfo.common.CommonPage;
import com.wwinfo.common.CommonResult;
import com.wwinfo.model.Alarm;
import com.wwinfo.pojo.query.AlarmQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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


    @ApiOperation(value = "分页获取所有报警记录")
    @GetMapping("/page")
    public CommonResult<CommonPage<Alarm>> page(AlarmQuery alarmQuery,
                                                @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
                                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {

       /* IPage page = logService.listPage(tLog, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));*/
        return null;
    }



}
