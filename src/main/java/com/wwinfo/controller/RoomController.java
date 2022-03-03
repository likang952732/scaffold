package com.wwinfo.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wwinfo.annotation.MyLog;
import com.wwinfo.common.CommonPage;
import com.wwinfo.common.CommonResult;
import com.wwinfo.model.Room;
import com.wwinfo.pojo.query.RoomQuery;
import com.wwinfo.pojo.vo.RoomAddVO;
import com.wwinfo.pojo.vo.RoomChgVO;
import com.wwinfo.service.RoomService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 库房
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Api(value = "库房接口", tags = "库房接口")
@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "分页获取库房")
    @PostMapping("/page")
    public CommonResult<CommonPage<Room>> page(RoomQuery roomQuery) {
        IPage page = roomService.listPage(roomQuery);
        return CommonResult.success(CommonPage.restPage(page));
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "获取库房列表")
    @PostMapping("/list")
    public CommonResult<List<Room>> list(RoomQuery roomQuery) {
        return CommonResult.success(roomService.list(roomQuery));
    }


    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "添加库房")
    @PostMapping("/add")
    @MyLog(operate = "添加", objectType = "添加库房", objectName = "库房管理", descript = "添加库房: #{#roomAddVO.roomName}")
    public CommonResult add(@Validated RoomAddVO roomAddVO, BindingResult result){
        List<FieldError> fieldErrors = result.getFieldErrors();
        if(!fieldErrors.isEmpty()){
            return CommonResult.failed(fieldErrors.get(0).getDefaultMessage());
        }
        int count = roomService.add(roomAddVO);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation("编辑库房")
    @PostMapping("/update")
    @MyLog(operate = "编辑", objectType = "编辑库房", objectName = "库房管理", descript = "编辑库房: #{#roomChgVO.roomName}")
    public CommonResult update(@Validated RoomChgVO roomChgVO, BindingResult result) {
        List<FieldError> fieldErrors = result.getFieldErrors();
        if(!fieldErrors.isEmpty()){
            return CommonResult.failed(fieldErrors.get(0).getDefaultMessage());
        }
        int count = roomService.update(roomChgVO);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation("删除库房")
    @PostMapping("/delete/{id}")
    @MyLog(operate = "删除", objectType = "删除库房", objectName = "库房管理", descript = "删除库房: #{id}")
    public CommonResult delete(@ApiParam(name="id",value="库房id",required=true)@PathVariable("id") Long id) {
        int count = roomService.delete(id);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

}
