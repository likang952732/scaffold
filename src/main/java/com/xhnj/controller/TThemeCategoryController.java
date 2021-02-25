package com.xhnj.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xhnj.common.CommonPage;
import com.xhnj.common.CommonResult;
import com.xhnj.model.TThemeCategory;
import com.xhnj.model.TThemeObj;
import com.xhnj.service.TThemeCategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 主题分类
 * </p>
 *
 * @author lk
 * @since 2021-02-25
 */
@RestController
@RequestMapping("/theme/category")
public class TThemeCategoryController {

    @Autowired
    private TThemeCategoryService themeCategoryService;

    @ApiOperation("获取所有主题分类")
    @GetMapping("/listAll")
    public CommonResult<List<TThemeCategory>> listAll() {
        return CommonResult.success(themeCategoryService.list());
    }

    @ApiOperation(value = "分页获取主题分类")
    @GetMapping("/list")
    public CommonResult<CommonPage<TThemeCategory>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                         @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
                                                         @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page page = themeCategoryService.listPage(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));
    }

    @ApiOperation(value = "获取指定主题分类下的分类对象")
    @GetMapping("/obj/{id}")
    public List<TThemeObj> list(@PathVariable("id") Long id, Integer type) {
        return themeCategoryService.getTThemeObj(id, type);
    }


    @ApiOperation("添加主题分类")
    @PostMapping("/add")
    public CommonResult create(@Validated TThemeCategory role, BindingResult result) {
        List<FieldError> fieldErrors = result.getFieldErrors();
        if(!fieldErrors.isEmpty()){
            return CommonResult.failed(fieldErrors.get(0).getDefaultMessage());
        }
        int count = themeCategoryService.create(role);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("编辑主题分类")
    @PutMapping("/update")
    public CommonResult update(TThemeCategory role) {
        int count = themeCategoryService.update(role);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("删除主题分类")
    @PostMapping("/delete/{id}")
    public CommonResult delete(@PathVariable("id") Long id) {
        int count = themeCategoryService.delete(id);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }
}
