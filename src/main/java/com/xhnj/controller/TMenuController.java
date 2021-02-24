package com.xhnj.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xhnj.common.CommonPage;
import com.xhnj.common.CommonResult;
import com.xhnj.model.TMenu;
import com.xhnj.service.ITMenuService;
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
@RestController
@RequestMapping("/menu")
public class TMenuController {
    @Autowired
    private ITMenuService menuService;

    @ApiOperation("获取所有菜单")
    @GetMapping("/listAll")
    public CommonResult<List<TMenu>> listAll() {
        return CommonResult.success(menuService.list());
    }

    @ApiOperation(value = "分页获取菜单")
    @GetMapping("/list")
    public CommonResult<CommonPage<TMenu>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
                                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page page = menuService.listPage(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));
    }

    @ApiOperation("添加菜单")
    @PostMapping("/add")
    public CommonResult create(@Validated TMenu role, BindingResult result) {
        List<FieldError> fieldErrors = result.getFieldErrors();
        if(!fieldErrors.isEmpty()){
            return CommonResult.failed(fieldErrors.get(0).getDefaultMessage());
        }
        int count = menuService.create(role);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("编辑菜单")
    @PutMapping("/update")
    public CommonResult update(TMenu role) {
        int count = menuService.update(role);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("删除菜单")
    @PostMapping("/delete/{id}")
    public CommonResult delete(@PathVariable("id") Long id) {
        int count = menuService.delete(id);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

}
