package com.wwinfo.controller;


import com.wwinfo.annotation.MyLog;
import com.wwinfo.common.CommonPage;
import com.wwinfo.common.CommonResult;
import com.wwinfo.model.Organize;
import com.wwinfo.pojo.bo.OrganizeNode;
import com.wwinfo.pojo.dto.UserChgParam;
import com.wwinfo.pojo.query.OrganizeQuery;
import com.wwinfo.pojo.vo.OrganizeAddVO;
import com.wwinfo.pojo.vo.OrganizeChgVO;
import com.wwinfo.pojo.vo.UserAddVO;
import io.swagger.annotations.*;
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


    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "分页获取父级部门")
    @PostMapping("/page")
    public CommonResult<CommonPage<Organize>> page(OrganizeQuery organizeQuery) {

       /* IPage page = logService.listPage(tLog, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));*/
        return null;
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "获取父级部门列表")
    @PostMapping("/list")
    public CommonResult<List<Organize>> list() {

       /* IPage page = logService.listPage(tLog, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));*/
        return null;
    }


    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "获取指定部门的下级部门列表")
    @PostMapping("/nextLevel/{orgID}")
    public CommonResult<List<Organize>> nextLevel(@ApiParam(name="orgID",value="部门ID",required=true)
                                                      @PathVariable("orgID")Long orgID) {

       /* IPage page = logService.listPage(tLog, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));*/
        return null;
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation("树形结构返回所有部门列表")
    @PostMapping("/treeList")
    public CommonResult<List<OrganizeNode>> treeList() {
       /* List<MenuNode> menuNodes = menuService.treeList();
        return CommonResult.success(menuNodes);*/
        return null;
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "添加部门")
    @PostMapping("/add")
    @MyLog(operate = "添加", objectType = "系统权限管理", objectName = "部门管理", descript = "添加部门: #{#admin.username}")
    public CommonResult add(OrganizeAddVO organizeAddVO, BindingResult result){
      /*  int count=adminService.insertAdmin(admin);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();*/
        return null;
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation("编辑部门")
    @PostMapping("/update")
    public CommonResult update(OrganizeChgVO organizeChgVO, BindingResult result) {
       /* int count = adminService.updateAdmin(admin);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();*/

        return null;
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation("删除部门")
    @PostMapping("/{orgID}")
    public CommonResult delete(@ApiParam(name="orgID",value="部门ID",required=true)@PathVariable("orgID") Long orgID) {
        /*int count = adminService.deleteAdmin(id);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();*/

        return null;
    }

    

}
