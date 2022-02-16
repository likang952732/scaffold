package com.wwinfo.controller;


import com.wwinfo.annotation.MyLog;
import com.wwinfo.common.CommonPage;
import com.wwinfo.common.CommonResult;
import com.wwinfo.model.Room;
import com.wwinfo.pojo.vo.RoomAddVO;
import com.wwinfo.pojo.vo.RoomChgVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "分页获取库房")
    @GetMapping("/page")
    public CommonResult<CommonPage<Room>> page(String roomName,
                                               @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
                                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {

       /* IPage page = logService.listPage(tLog, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));*/
        return null;
    }


    @ApiOperation(value = "添加库房")
    @PostMapping("/add")
    @MyLog(operate = "添加", objectType = "添加库房", objectName = "库房管理", descript = "添加库房: #{#admin.username}")
    public CommonResult add(@RequestBody RoomAddVO roomAddVO, BindingResult result){
      /*  int count=adminService.insertAdmin(admin);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();*/
        return null;
    }

    @ApiOperation("编辑库房")
    @PutMapping("/update")
    public CommonResult update(@RequestBody RoomChgVO roomChgVO, BindingResult result) {
       /* int count = adminService.updateAdmin(admin);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();*/

        return null;
    }

    @ApiOperation("删除库房")
    @DeleteMapping("/{id}")
    public CommonResult delete(@ApiParam(name="id",value="库房id",required=true)@PathVariable("id") Long id) {
        /*int count = adminService.deleteAdmin(id);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();*/

        return null;
    }

}
