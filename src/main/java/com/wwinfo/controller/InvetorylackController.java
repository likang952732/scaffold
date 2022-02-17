package com.wwinfo.controller;


import com.wwinfo.common.CommonPage;
import com.wwinfo.common.CommonResult;
import com.wwinfo.pojo.query.InvetorylackQuery;
import com.wwinfo.pojo.res.InvetorylackRes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 资产盘点缺少
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Api(value = "资产盘点缺少接口", tags = "资产盘点缺少接口")
@RestController
@RequestMapping("/invetorylack")
public class InvetorylackController {


    @ApiOperation(value = "分页获取资产盘点缺少")
    @PostMapping("/page")
    public CommonResult<CommonPage<InvetorylackRes>> page(@RequestBody InvetorylackQuery invetorylackQuery) {

       /* IPage page = logService.listPage(tLog, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));*/
        return null;
    }

}
