package com.example.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.common.CommonPage;
import com.example.common.CommonResult;
import com.example.dto.UserOrderParam;
import com.example.model.UserOrder;
import com.example.service.UserOrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  用户发布订餐
 * </p>
 *
 * @author lk
 * @since 2021-06-25
 */
@RestController
@RequestMapping("/user/order")
public class UserOrderController {
    @Autowired
    private UserOrderService userOrderService;

    @ApiOperation(value = "分页获取用户发布的订餐订单")
    @GetMapping("/list")
    public CommonResult<CommonPage<UserOrder>> list(UserOrderParam userOrderParam,
                                                    @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
                                                    @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        IPage page = userOrderService.listPage(userOrderParam, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));
    }


    @ApiOperation("用户发布订餐订单")
    @PostMapping("/add")
    public CommonResult create(@Validated @RequestBody UserOrder userOrder, BindingResult result) {
        List<FieldError> fieldErrors = result.getFieldErrors();
        if(!fieldErrors.isEmpty()){
            return CommonResult.failed(fieldErrors.get(0).getDefaultMessage());
        }
        int count = userOrderService.create(userOrder);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }
}
