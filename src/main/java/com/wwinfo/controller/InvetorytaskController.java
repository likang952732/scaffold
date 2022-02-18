package com.wwinfo.controller;


import com.wwinfo.annotation.MyLog;
import com.wwinfo.common.CommonPage;
import com.wwinfo.common.CommonResult;
import com.wwinfo.pojo.dto.InvTaskCancelParam;
import com.wwinfo.pojo.query.InvetorytaskQuery;
import com.wwinfo.pojo.res.InvetorytaskRes;
import com.wwinfo.pojo.vo.InvetorytaskAddVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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


    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "分页获取资产盘点任务")
    @PostMapping("/page")
    public CommonResult<CommonPage<InvetorytaskRes>> page(InvetorytaskQuery invetorytaskQuery) {

       /* IPage page = logService.listPage(tLog, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));*/
        return null;
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "添加资产盘点任务")
    @PostMapping("/add")
    @MyLog(operate = "添加", objectType = "添加资产盘点任务", objectName = "添加资产盘点任务", descript = "添加资产盘点任务: #{#admin.username}")
    public CommonResult add(InvetorytaskAddVO invetorytaskAddVO, BindingResult result){
      /*  int count=adminService.insertAdmin(admin);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();*/
        return null;
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation("取消")
    @PostMapping("/cancel")
    @MyLog(operate = "取消", objectType = "盘点任务取消", objectName = "盘点任务取消", descript = "盘点任务取消")
    public CommonResult cancel(InvTaskCancelParam invTaskCancelParam, BindingResult result) {
     /*   int count = adminService.updatePass(UmsAdminUpdatePassParam);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();*/
        return null;
    }

}
