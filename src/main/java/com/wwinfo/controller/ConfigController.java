package com.wwinfo.controller;


import com.wwinfo.annotation.MyLog;
import com.wwinfo.common.CommonPage;
import com.wwinfo.common.CommonResult;
import com.wwinfo.model.TConfig;
import com.wwinfo.pojo.query.ConfigQuery;
import com.wwinfo.pojo.vo.ConfigAddVO;
import com.wwinfo.pojo.vo.ConfigChgVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 参数配置
 * </p>
 *
 * @author lk
 * @since 2022-02-17
 */
@Api(value = "参数配置接口", tags = "参数配置接口")
@RestController
@RequestMapping("/config")
public class ConfigController {

    @ApiOperation(value = "分页获取参数配置")
    @PostMapping("/page")
    public CommonResult<CommonPage<TConfig>> page(@RequestBody ConfigQuery configQuery) {

       /* IPage page = logService.listPage(tLog, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));*/
        return null;
    }


    @ApiOperation(value = "添加参数配置")
    @PostMapping("/add")
    @MyLog(operate = "添加", objectType = "添加参数配置", objectName = "参数配置管理", descript = "添加参数配置: #{#admin.username}")
    public CommonResult add(@RequestBody ConfigAddVO configAddVO, BindingResult result){
      /*  int count=adminService.insertAdmin(admin);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();*/
        return null;
    }

    @ApiOperation("编辑参数配置")
    @PostMapping("/update")
    public CommonResult update(@RequestBody ConfigChgVO configChgVO, BindingResult result) {
       /* int count = adminService.updateAdmin(admin);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();*/

        return null;
    }

    @ApiOperation("删除参数配置")
    @PostMapping("/{id}")
    public CommonResult delete(@ApiParam(name="id",value="参数配置id",required=true)@PathVariable("id") Long id) {
        /*int count = adminService.deleteAdmin(id);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();*/

        return null;
    }

}
