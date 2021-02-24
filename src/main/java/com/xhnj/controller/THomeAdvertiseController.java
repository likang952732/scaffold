package com.xhnj.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xhnj.common.CommonPage;
import com.xhnj.common.CommonResult;
import com.xhnj.model.THomeAdvertise;
import com.xhnj.service.THomeAdvertiseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 首页轮播广告
 * </p>
 *
 * @author lk
 * @since 2021-02-24
 */
@RestController
@RequestMapping("/home/advertise")
public class THomeAdvertiseController {
    @Autowired
    private THomeAdvertiseService homeAdvertiseService;

    @ApiOperation(value = "分页获取角色列表数据")
    @GetMapping("/list")
    public CommonResult<CommonPage<THomeAdvertise>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                         @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
                                                         @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page page = homeAdvertiseService.listPage(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));
    }

    @ApiOperation("添加角色")
    @PostMapping("/add")
    public CommonResult create(@Validated THomeAdvertise homeAdvertise, BindingResult result) {
        List<FieldError> fieldErrors = result.getFieldErrors();
        if(!fieldErrors.isEmpty()){
            return CommonResult.failed(fieldErrors.get(0).getDefaultMessage());
        }
        int count = homeAdvertiseService.create(homeAdvertise);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("编辑角色")
    @PutMapping("/update")
    public CommonResult update(THomeAdvertise homeAdvertise) {
        int count = homeAdvertiseService.update(homeAdvertise);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("删除角色")
    @PostMapping("/delete/{id}")
    public CommonResult delete(@PathVariable("id") Long id) {
        int count = homeAdvertiseService.delete(id);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

}
