package com.wwinfo.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
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
import com.wwinfo.service.OrganizeService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
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

    @Autowired
    private OrganizeService organizeService;


    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "分页获取父级部门")
    @PostMapping("/page")
    public CommonResult<CommonPage<Organize>> page(OrganizeQuery organizeQuery) {
        IPage page = organizeService.listPage(organizeQuery);
        return CommonResult.success(CommonPage.restPage(page));
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "获取父级部门列表")
    @PostMapping("/list")
    public CommonResult<List<Organize>> list() {
        return CommonResult.success(organizeService.list());
    }


    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "获取指定部门的下级部门列表")
    @PostMapping("/nextLevel/{orgID}")
    public CommonResult<CommonPage<Organize>> nextLevel(@ApiParam(name="orgID",value="部门ID",required=true)
                                                        @PathVariable("orgID")Long orgID,
                                                        @ApiParam(name="pageSize",value="pageSize(默认10条)")
                                                        @RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize,
                                                        @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {

        IPage page = organizeService.nextLevel(orgID, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation("树形结构返回所有部门列表")
    @PostMapping("/treeList")
    public CommonResult<List<OrganizeNode>> treeList() {
        List<OrganizeNode> organizeNodes = organizeService.treeList();
        return CommonResult.success(organizeNodes);
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "添加部门")
    @PostMapping("/add")
    @MyLog(operate = "添加", objectType = "系统权限管理", objectName = "部门管理", descript = "添加部门: #{#organizeAddVO.orgName}")
    public CommonResult add(@Validated OrganizeAddVO organizeAddVO, BindingResult result){
        List<FieldError> fieldErrors = result.getFieldErrors();
        if(!fieldErrors.isEmpty()){
            return CommonResult.failed(fieldErrors.get(0).getDefaultMessage());
        }
        int count = organizeService.add(organizeAddVO);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation("编辑部门")
    @PostMapping("/update")
    @MyLog(operate = "编辑", objectType = "系统权限管理", objectName = "部门管理", descript = "编辑部门: #{#organizeChgVO.orgName}")
    public CommonResult update(@Validated OrganizeChgVO organizeChgVO, BindingResult result) {
        List<FieldError> fieldErrors = result.getFieldErrors();
        if(!fieldErrors.isEmpty()){
            return CommonResult.failed(fieldErrors.get(0).getDefaultMessage());
        }
        int count = organizeService.update(organizeChgVO);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation("删除部门")
    @PostMapping("/delete/{orgID}")
    @MyLog(operate = "删除", objectType = "系统权限管理", objectName = "部门管理", descript = "删除部门: #{#orgID}")
    public CommonResult delete(@ApiParam(name="orgID",value="部门ID",required=true)@PathVariable("orgID") Long orgID) {
        int count = organizeService.delete(orgID);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

}
