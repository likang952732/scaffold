package com.wwinfo.controller;


import com.wwinfo.common.CommonPage;
import com.wwinfo.common.CommonResult;
import com.wwinfo.model.TSyslog;
import com.wwinfo.pojo.query.SysLogQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

    @ApiOperation(value = "分页获取日志")
    @PostMapping("/page")
    public CommonResult<CommonPage<TSyslog>> page(@RequestBody SysLogQuery sysLogQuery) {

       /* IPage page = logService.listPage(tLog, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));*/
        return null;
    }

    @ApiOperation("删除日志")
    @PostMapping("/{delDate}")
    public CommonResult delete(@ApiParam(name="delDate",value="删除日期(yyyy-MM-dd)",required=true)@PathVariable String delDate) {
        /*int count = adminService.deleteAdmin(id);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();*/

        return null;
    }

}
