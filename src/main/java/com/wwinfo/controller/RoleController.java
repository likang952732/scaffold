package com.wwinfo.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwinfo.annotation.MyLog;
import com.wwinfo.common.CommonPage;
import com.wwinfo.common.CommonResult;
import com.wwinfo.model.TMenu;
import com.wwinfo.model.TRole;
import com.wwinfo.pojo.vo.RoleAddVO;
import com.wwinfo.pojo.vo.RoleChgVO;
import com.wwinfo.service.TRoleService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 角色管理
 * </p>
 *
 * @author lk
 * @since 2021-02-24
 */
@Api(value = "角色接口", tags = "角色接口")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private TRoleService roleService;

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation("获取所有角色")
    @PostMapping("/listAll")
    public CommonResult<List<TRole>> listAll() {
        return CommonResult.success(roleService.listAll());
    }

    @ApiOperation(value = "分页获取角色列表数据")
    @PostMapping("/list")
    public CommonResult<CommonPage<TRole>> list(@RequestParam(value = "name", required = false) String name,
                                                @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
                                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page page = roleService.listPage(name, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation("获取指定角色的菜单")
    @PostMapping("/menu/{id}")
    public CommonResult<List<TMenu>> menu(@PathVariable("id") Long id) {
        return CommonResult.success(roleService.getUmsMenu(id));
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation("添加角色")
    @PostMapping("/add")
    @MyLog(operate = "添加", objectType = "系统权限管理", objectName = "角色管理", descript = "添加角色: #{#roleAddVO.name}")
    public CommonResult create(@Validated RoleAddVO roleAddVO, BindingResult result) {
        List<FieldError> fieldErrors = result.getFieldErrors();
        if(!fieldErrors.isEmpty()){
            return CommonResult.failed(fieldErrors.get(0).getDefaultMessage());
        }
        int count = roleService.create(roleAddVO);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation("编辑角色")
    @PostMapping("/update")
    @MyLog(operate = "修改", objectType = "系统权限管理", objectName = "角色管理", descript = "编辑角色")
    public CommonResult update(@Validated RoleChgVO roleChgVO, BindingResult result) {
        List<FieldError> fieldErrors = result.getFieldErrors();
        if(!fieldErrors.isEmpty()){
            return CommonResult.failed(fieldErrors.get(0).getDefaultMessage());
        }
        int count = roleService.update(roleChgVO);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation("删除角色")
    @PostMapping("/delete/{id}")
    @MyLog(operate = "删除", objectType = "系统权限管理", objectName = "角色管理", descript = "删除角色: #{#id}")
    public CommonResult delete(@PathVariable("id") Long id) {
        int count = roleService.delete(id);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation("分配菜单")
    @PostMapping("/allocMenu")
    @MyLog(operate = "修改", objectType = "系统权限管理", objectName = "角色管理", descript = "分配菜单")
    public CommonResult allocMenu(@ApiParam(name="roleId",value="角色id",required=true)@RequestParam Long roleId,
                                  @ApiParam(name="menuIds",value="菜单id列表(多个用逗号分隔)",required=true)@RequestParam List<Long> menuIds) {
        int count = roleService.allocMenu(roleId, menuIds);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

}
