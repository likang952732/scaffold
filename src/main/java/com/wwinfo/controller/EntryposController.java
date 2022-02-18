package com.wwinfo.controller;


import com.wwinfo.annotation.MyLog;
import com.wwinfo.common.CommonPage;
import com.wwinfo.common.CommonResult;
import com.wwinfo.pojo.query.EntryposQuery;
import com.wwinfo.pojo.res.EntryposRes;
import com.wwinfo.pojo.vo.EntryposAddVO;
import com.wwinfo.pojo.vo.EntryposChgVO;
import io.swagger.annotations.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * <p>
 * RFID进出口设置
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Api(value = "RFID进出口设置接口", tags = "RFID进出口设置接口")
@RestController
@RequestMapping("/entrypos")
public class EntryposController {

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "分页获取RFID进出口设置")
    @PostMapping("/page")
    public CommonResult<CommonPage<EntryposRes>> page(EntryposQuery entryposQuery) {

       /* IPage page = logService.listPage(tLog, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));*/
        return null;
    }


    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "添加RFID进出口设置")
    @PostMapping("/add")
    @MyLog(operate = "添加", objectType = "添加RFID进出口设置", objectName = "RFID进出口设置管理", descript = "添加RFID进出口设置: #{#admin.username}")
    public CommonResult add(EntryposAddVO entryposAddVO, BindingResult result){
      /*  int count=adminService.insertAdmin(admin);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();*/
        return null;
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation("编辑RFID进出口设置")
    @PostMapping("/update")
    public CommonResult update(EntryposChgVO entryposChgVO, BindingResult result) {
       /* int count = adminService.updateAdmin(admin);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();*/

        return null;
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation("删除RFID进出口设置")
    @PostMapping("/{id}")
    public CommonResult delete(@ApiParam(name="id",value="RFID进出口设置id",required=true)@PathVariable("id") Long id) {
        /*int count = adminService.deleteAdmin(id);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();*/

        return null;
    }


}
