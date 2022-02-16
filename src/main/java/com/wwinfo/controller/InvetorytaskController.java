package com.wwinfo.controller;


import com.wwinfo.annotation.MyLog;
import com.wwinfo.common.CommonPage;
import com.wwinfo.common.CommonResult;
import com.wwinfo.pojo.dto.InvTaskCancelParam;
import com.wwinfo.pojo.dto.UserChgpwdParam;
import com.wwinfo.pojo.res.InvetorytaskRes;
import com.wwinfo.pojo.vo.InvetorytaskAddVO;
import com.wwinfo.pojo.vo.UserAddVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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


    @ApiOperation(value = "分页获取资产盘点任务")
    @GetMapping("/page")
    public CommonResult<CommonPage<InvetorytaskRes>> page(@ApiParam(name="status",value="盘点状态")Integer status,
                                                          @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
                                                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {

       /* IPage page = logService.listPage(tLog, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));*/
        return null;
    }

    @ApiOperation(value = "添加资产盘点任务")
    @PostMapping("/add")
    @MyLog(operate = "添加", objectType = "添加资产盘点任务", objectName = "添加资产盘点任务", descript = "添加资产盘点任务: #{#admin.username}")
    public CommonResult add(@RequestBody InvetorytaskAddVO invetorytaskAddVO, BindingResult result){
      /*  int count=adminService.insertAdmin(admin);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();*/
        return null;
    }

    @ApiOperation("取消")
    @PostMapping("/cancel")
    @MyLog(operate = "取消", objectType = "盘点任务取消", objectName = "盘点任务取消", descript = "盘点任务取消")
    public CommonResult cancel(@RequestBody InvTaskCancelParam invTaskCancelParam, BindingResult result) {
     /*   int count = adminService.updatePass(UmsAdminUpdatePassParam);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();*/
        return null;
    }

}
