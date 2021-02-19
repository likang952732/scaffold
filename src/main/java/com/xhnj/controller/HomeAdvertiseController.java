package com.xhnj.controller;

import com.xhnj.common.CommonPage;
import com.xhnj.common.CommonResult;
import com.xhnj.model.HomeAdvertise;
import com.xhnj.service.IHomeAdvertiseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 @Description 首页轮播广告
 *@author kang.li
 *@date 2021/2/19 14:02   
 */
@RestController
@RequestMapping("/home/advertise")
public class HomeAdvertiseController {
    @Autowired
    private IHomeAdvertiseService homeAdvertiseService;


    @ApiOperation("分页查询首页轮播广告")
    @GetMapping("/list")
    public CommonResult<CommonPage<HomeAdvertise>> list(@RequestParam(value = "status", required = false)Integer status,
                                                        @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
                                                        @RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum) {
        List<HomeAdvertise> list = homeAdvertiseService.list(status, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @ApiOperation("添加首页轮播广告")
    @PostMapping("/create")
    public CommonResult create(HomeAdvertise homeAdvertise) {
        int count = homeAdvertiseService.create(homeAdvertise);
        if(count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("编辑首页轮播广告")
    @PutMapping("/update")
    public CommonResult updateStatus(HomeAdvertise homeAdvertise) {
        int count = homeAdvertiseService.update(homeAdvertise);
        if(count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("删除首页轮播广告")
    @DeleteMapping("/delete")
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        int count = homeAdvertiseService.delete(ids);
        if(count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
}
