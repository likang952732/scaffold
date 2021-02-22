package com.xhnj.controller;

import com.xhnj.common.CommonPage;
import com.xhnj.common.CommonResult;
import com.xhnj.model.UmsMenu;
import com.xhnj.model.UmsRole;
import com.xhnj.service.UmsRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 @Description 角色管理
 *@author kang.li
 *@date 2021/2/20 15:37   
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private UmsRoleService roleService;

    @ApiOperation("获取所有角色")
    @GetMapping("/listAll")
    public CommonResult<List<UmsRole>> listAll() {
        return CommonResult.success(roleService.list());
    }


    @ApiOperation(value = "分页获取角色列表数据")
    @GetMapping("/list")
    public CommonResult<CommonPage<UmsRole>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                   @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<UmsRole> roleList = roleService.listPage(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(roleList));
    }

    @ApiOperation("获取角色的菜单")
    @GetMapping("/listMenu/{id}")
    public CommonResult<List<UmsMenu>> listMenu(@PathVariable("id")Long id) {
        List<UmsMenu> menus = roleService.listMenu(id);
        return CommonResult.success(menus);
    }

    @ApiOperation("创建角色")
    @PostMapping("/create")
    public CommonResult create(UmsRole role) {
        int count = roleService.create(role);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("编辑角色")
    @PutMapping("/update")
    public CommonResult update(UmsRole role) {
        int count = roleService.updateUmsRole(role.getId(), role);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("删除用户")
    @PostMapping("/delete/{id}")
    public CommonResult delete(@PathVariable("id") Long id) {
        int count = roleService.delete(id);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("分配菜单")
    @PostMapping("/allocMenu")
    public CommonResult allocMenu(@RequestParam Long roleId, @RequestParam List<Long> menuIds) {
        int count = roleService.allocMenu(roleId, menuIds);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }


}
