package com.wwinfo.controller;


import com.wwinfo.annotation.MyLog;
import com.wwinfo.common.CommonPage;
import com.wwinfo.common.CommonResult;
import com.wwinfo.model.Organize;
import com.wwinfo.pojo.dto.UserChgParam;
import com.wwinfo.pojo.query.OrganizeQuery;
import com.wwinfo.pojo.vo.OrganizeAddVO;
import com.wwinfo.pojo.vo.OrganizeChgVO;
import com.wwinfo.pojo.vo.UserAddVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 部门管理
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Api(value = "部门管理接口", tags = "部门管理接口")
@RestController
@RequestMapping("/organize")
public class OrganizeController {

    @ApiOperation(value = "分页获取父级部门")
    @GetMapping("/page")
    public CommonResult<CommonPage<Organize>> page(OrganizeQuery organizeQuery,
                                                   @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {

       /* IPage page = logService.listPage(tLog, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));*/
        return null;
    }

    @ApiOperation(value = "获取父级部门列表")
    @GetMapping("/list")
    public CommonResult<List<Organize>> list() {

       /* IPage page = logService.listPage(tLog, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));*/
        return null;
    }


    @ApiOperation(value = "获取下级部门列表")
    @GetMapping("/nextLevel/{orgID}")
    public CommonResult<List<Organize>> nextLevel(@ApiParam(name="orgID",value="部门ID",required=true)
                                                      @PathVariable("orgID")Long orgID) {

       /* IPage page = logService.listPage(tLog, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));*/
        return null;
    }

    @ApiOperation(value = "添加部门")
    @PostMapping("/add")
    @MyLog(operate = "添加", objectType = "系统权限管理", objectName = "部门管理", descript = "添加部门: #{#admin.username}")
    public CommonResult add(@RequestBody OrganizeAddVO organizeAddVO, BindingResult result){
      /*  int count=adminService.insertAdmin(admin);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();*/
        return null;
    }

    @ApiOperation("编辑部门")
    @PutMapping("/update")
    public CommonResult update(@RequestBody OrganizeChgVO organizeChgVO, BindingResult result) {
       /* int count = adminService.updateAdmin(admin);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();*/

        return null;
    }

    @ApiOperation("删除部门")
    @DeleteMapping("/{orgID}")
    public CommonResult delete(@ApiParam(name="orgID",value="部门ID",required=true)@PathVariable("orgID") Long orgID) {
        /*int count = adminService.deleteAdmin(id);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();*/

        return null;
    }

    

}
