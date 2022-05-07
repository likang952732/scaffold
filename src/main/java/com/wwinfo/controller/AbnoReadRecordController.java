package com.wwinfo.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wwinfo.common.CommonPage;
import com.wwinfo.common.CommonResult;
import com.wwinfo.pojo.query.AbnoReadRecordQuery;
import com.wwinfo.pojo.query.PersonnelLibQuery;
import com.wwinfo.pojo.res.AbnoReadRecordRes;
import com.wwinfo.pojo.res.PersonnelLibRes;
import com.wwinfo.service.AbnoReadRecordService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 异常读取记录表 前端控制器
 * </p>
 *
 * @author lk
 * @since 2022-05-03
 */
@RestController
@RequestMapping("/abnorecord")
public class AbnoReadRecordController {

    @Autowired
    private AbnoReadRecordService abnoReadRecordService;

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "分页获取异常读取记录")
    @PostMapping("/page")
    public CommonResult<CommonPage<AbnoReadRecordRes>> page(AbnoReadRecordQuery query) {
        IPage page = abnoReadRecordService.listPage(query);
        return CommonResult.success(CommonPage.restPage(page));
    }

}
