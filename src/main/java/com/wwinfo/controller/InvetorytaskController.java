package com.wwinfo.controller;


import com.wwinfo.common.CommonPage;
import com.wwinfo.common.CommonResult;
import com.wwinfo.pojo.res.InvetorytaskRes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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


    @ApiOperation(value = "分页获取资产盘点任务")
    @GetMapping("/page")
    public CommonResult<CommonPage<InvetorytaskRes>> page(@ApiParam(name="status",value="盘点状态")Integer status,
                                                          @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
                                                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {

       /* IPage page = logService.listPage(tLog, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));*/
        return null;
    }

}
