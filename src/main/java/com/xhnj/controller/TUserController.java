package com.xhnj.controller;


import com.xhnj.common.CommonResult;
import com.xhnj.dto.UmsAdminLoginParam;
import com.xhnj.model.TAdmin;
import com.xhnj.model.TUser;
import com.xhnj.service.ITUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 小程序用户
 * </p>
 *
 * @author lk
 * @since 2021-02-24
 */
@RestController
@RequestMapping("/user")
public class TUserController {
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private ITUserService userService;

    @ApiOperation(value = "登录以后返回token")
    @PostMapping("/login")
    public CommonResult login(UmsAdminLoginParam umsAdminLoginParam, BindingResult result) {
        String token = userService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public CommonResult<TUser> register(TUser user, BindingResult result) {
        List<FieldError> fieldErrors = result.getFieldErrors();
        if(!fieldErrors.isEmpty()){
            return CommonResult.failed(fieldErrors.get(0).getDefaultMessage());
        }
        userService.register(user);
        if(user == null)
            return CommonResult.failed();
        return CommonResult.success(user);
    }

    @ApiOperation(value = "获取登录用户信息")
    @GetMapping("/info")
    public CommonResult<Map> info(Principal principal) {
        if (principal == null)
            return CommonResult.unauthorized(null);
        TUser user = userService.getUserByUsername(principal.getName());
        Long id = user.getId();
        Map<String, Object> data = new HashMap<>();
        data.put("username", user.getUsername());
        data.put("icon", user.getIcon());
        return CommonResult.success(data);
    }

    @ApiOperation(value = "退出")
    @PostMapping("/logout")
    public CommonResult logout() {
        return CommonResult.success(null);
    }

}
