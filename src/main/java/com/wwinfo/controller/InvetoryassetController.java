package com.wwinfo.controller;


import com.wwinfo.common.CommonPage;
import com.wwinfo.common.CommonResult;
import com.wwinfo.pojo.query.InvetoryassetQuery;
import com.wwinfo.pojo.res.InvetoryassetRes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 资产盘点清单
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Api(value = "资产盘点清单接口", tags = "资产盘点清单接口")
@RestController
@RequestMapping("/invetoryasset")
public class InvetoryassetController {


    @ApiOperation(value = "分页获取资产盘点清单")
    @PostMapping("/page")
    public CommonResult<CommonPage<InvetoryassetRes>> page(@RequestBody InvetoryassetQuery invetoryassetQuery) {

       /* IPage page = logService.listPage(tLog, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));*/
        return null;
    }

}
