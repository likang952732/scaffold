package com.wwinfo.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwinfo.common.CommonPage;
import com.wwinfo.common.CommonResult;
import com.wwinfo.model.Alarm;
import com.wwinfo.pojo.query.AlarmQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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


    @ApiOperation(value = "分页获取所有报警记录")
    @PostMapping("/page")
    public CommonResult<CommonPage<Alarm>> page(@RequestBody AlarmQuery alarmQuery) {

       /* IPage page = logService.listPage(tLog, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));*/
        return null;
    }



}
