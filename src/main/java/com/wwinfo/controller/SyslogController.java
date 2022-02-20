package com.wwinfo.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wwinfo.common.CommonPage;
import com.wwinfo.common.CommonResult;
import com.wwinfo.model.TSyslog;
import com.wwinfo.pojo.query.SysLogQuery;
import com.wwinfo.service.TSyslogService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 日志
 * </p>
 * @author lk
 * @since 2022-02-14
 */
@Api(value = "日志管理接口", tags = "日志管理接口")
@RestController
@RequestMapping("/syslog")
public class SyslogController {

    @Autowired
    private TSyslogService syslogService;


    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "分页获取日志")
    @PostMapping("/page")
    public CommonResult<CommonPage<TSyslog>> page(SysLogQuery sysLogQuery) {
        IPage page = syslogService.listPage(sysLogQuery);
        return CommonResult.success(CommonPage.restPage(page));
    }


    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation("删除日志")
    @PostMapping("/delete/{delDate}")
    public CommonResult delete(@ApiParam(name="delDate",value="删除日期(yyyy-MM-dd)",required=true)@PathVariable String delDate) {
        int count = syslogService.delete(delDate);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

}
