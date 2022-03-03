package com.wwinfo.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwinfo.annotation.MyLog;
import com.wwinfo.common.CommonPage;
import com.wwinfo.common.CommonResult;
import com.wwinfo.model.TMenu;
import com.wwinfo.pojo.bo.MenuNode;
import com.wwinfo.pojo.dto.MenuParam;
import com.wwinfo.pojo.vo.MenuAddVO;
import com.wwinfo.pojo.vo.MenuChgVO;
import com.wwinfo.service.TMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 菜单管理
 * </p>
 *
 * @author lk
 * @since 2021-02-24
 */
@Api(value = "菜单接口", tags = "菜单接口")
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private TMenuService menuService;

    /*@ApiOperation("获取所有菜单")
    @GetMapping("/listAll")
    public CommonResult<List<TMenu>> listAll() {
        return CommonResult.success(menuService.list());
    }*/

   /* @ApiOperation("根据级数获取菜单")
    @PostMapping("/listLevel")
    public CommonResult<CommonPage<TMenu>> listLevel(MenuParam menuParam,
                                               @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
                                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page page = menuService.listLevel(menuParam, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));
    }*/

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "分页获取菜单")
    @PostMapping("/list/{parentId}")
    public CommonResult<CommonPage<TMenu>> list(MenuParam menuParam,
                                                @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
                                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page page = menuService.listPage(menuParam, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation("树形结构返回所有菜单列表")
    @PostMapping("/treeList")
    public CommonResult<List<MenuNode>> treeList() {
        List<MenuNode> menuNodes = menuService.treeList();
        return CommonResult.success(menuNodes);
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation("添加菜单")
    @PostMapping("/add")
    @MyLog(operate = "添加", objectType = "菜单", objectName = "菜单管理", descript = "添加菜单: #{#menuAddVO.name}")
    public CommonResult create(@Validated MenuAddVO menuAddVO, BindingResult result) {
        List<FieldError> fieldErrors = result.getFieldErrors();
        if(!fieldErrors.isEmpty()){
            return CommonResult.failed(fieldErrors.get(0).getDefaultMessage());
        }
        int count = menuService.create(menuAddVO);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation("编辑菜单")
    @PostMapping("/update")
    @MyLog(operate = "修改", objectType = "菜单", objectName = "菜单管理", descript = "编辑菜单")
    public CommonResult update(@Validated MenuChgVO menuChgVO, BindingResult result) {
        List<FieldError> fieldErrors = result.getFieldErrors();
        if(!fieldErrors.isEmpty()){
            return CommonResult.failed(fieldErrors.get(0).getDefaultMessage());
        }
        int count = menuService.update(menuChgVO);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation("删除菜单")
    @PostMapping("/delete/{id}")
    @MyLog(operate = "删除", objectType = "菜单", objectName = "菜单管理", descript = "删除菜单: #{#id}")
    public CommonResult delete(@PathVariable("id") Long id) {
        int count = menuService.delete(id);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

}
