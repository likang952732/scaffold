package com.wwinfo.controller;


import com.wwinfo.annotation.MyLog;
import com.wwinfo.common.CommonPage;
import com.wwinfo.common.CommonResult;
import com.wwinfo.pojo.query.EntryposQuery;
import com.wwinfo.pojo.res.EntryposRes;
import com.wwinfo.pojo.vo.EntryposAddVO;
import com.wwinfo.pojo.vo.EntryposChgVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "分页获取RFID进出口设置")
    @GetMapping("/page")
    public CommonResult<CommonPage<EntryposRes>> page(EntryposQuery entryposQuery,
                                                      @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
                                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {

       /* IPage page = logService.listPage(tLog, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));*/
        return null;
    }


    @ApiOperation(value = "添加RFID进出口设置")
    @PostMapping("/add")
    @MyLog(operate = "添加", objectType = "添加RFID进出口设置", objectName = "RFID进出口设置管理", descript = "添加RFID进出口设置: #{#admin.username}")
    public CommonResult add(@RequestBody EntryposAddVO entryposAddVO, BindingResult result){
      /*  int count=adminService.insertAdmin(admin);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();*/
        return null;
    }

    @ApiOperation("编辑RFID进出口设置")
    @PutMapping("/update")
    public CommonResult update(@RequestBody EntryposChgVO entryposChgVO, BindingResult result) {
       /* int count = adminService.updateAdmin(admin);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();*/

        return null;
    }

    @ApiOperation("删除RFID进出口设置")
    @DeleteMapping("/{id}")
    public CommonResult delete(@ApiParam(name="id",value="RFID进出口设置id",required=true)@PathVariable("id") Long id) {
        /*int count = adminService.deleteAdmin(id);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();*/

        return null;
    }


}
