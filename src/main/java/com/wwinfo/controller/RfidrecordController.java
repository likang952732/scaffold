package com.wwinfo.controller;


import com.wwinfo.common.CommonPage;
import com.wwinfo.common.CommonResult;
import com.wwinfo.model.Rfidrecord;
import com.wwinfo.pojo.query.RfidrecordQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * RFID阅读记录
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Api(value = "RFID阅读记录接口", tags = "RFID阅读记录接口")
@RestController
@RequestMapping("/rfidrecord")
public class RfidrecordController {


    @ApiOperation(value = "分页获取RFID阅读记录")
    @PostMapping("/page")
    public CommonResult<CommonPage<Rfidrecord>> page(@RequestBody RfidrecordQuery rfidrecordQuery) {

       /* IPage page = logService.listPage(tLog, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));*/
        return null;
    }

}
