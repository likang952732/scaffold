package com.wwinfo.controller;


import com.wwinfo.annotation.MyLog;
import com.wwinfo.common.CommonPage;
import com.wwinfo.common.CommonResult;
import com.wwinfo.model.Rfidreader;
import com.wwinfo.pojo.query.RfidreaderQuery;
import com.wwinfo.pojo.vo.RfidreaderAddVO;
import com.wwinfo.pojo.vo.RfidreaderChgParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * RFID阅读器
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Api(value = "RFID阅读器接口", tags = "RFID阅读器接口")
@RestController
@RequestMapping("/rfidreader")
public class RfidreaderController {

    @ApiOperation(value = "分页获取RFID阅读器")
    @GetMapping("/page")
    public CommonResult<CommonPage<Rfidreader>> page(RfidreaderQuery rfidreaderQuery,
                                                     @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
                                                     @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {

       /* IPage page = logService.listPage(tLog, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));*/
        return null;
    }

    @ApiOperation(value = "添加RFID阅读器")
    @PostMapping("/add")
    @MyLog(operate = "添加", objectType = "添加RFID阅读器", objectName = "添加RFID阅读器", descript = "添加RFID阅读器: #{#admin.username}")
    public CommonResult add(@RequestBody RfidreaderAddVO rfidreaderAddVO, BindingResult result){
      /*  int count=adminService.insertAdmin(admin);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();*/
        return null;
    }

    @ApiOperation("编辑RFID阅读器")
    @PutMapping("/update")
    public CommonResult update(@RequestBody RfidreaderChgParam rfidreaderChgParam, BindingResult result) {
       /* int count = adminService.updateAdmin(admin);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();*/

        return null;
    }

    @ApiOperation("删除RFID阅读器")
    @DeleteMapping("/{id}")
    public CommonResult delete(@ApiParam(name="id",value="RFID阅读器id",required=true)@PathVariable("id") Long id) {
        /*int count = adminService.deleteAdmin(id);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();*/

        return null;
    }

}
