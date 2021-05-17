package com.example.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.CommonPage;
import com.example.common.CommonResult;
import com.example.dto.UmsAdminLoginParam;
import com.example.model.TAdmin;
import com.example.model.TOrder;
import com.example.service.TAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  后台用户管理
 * </p>
 *
 * @author lk
 * @since 2021-02-24
 */
@Api(value = "用户接口", tags = "用户接口类")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private TAdminService adminService;

    @ApiOperation(value = "分页获取用户列表数据")
    @GetMapping("/list")
    public CommonResult<CommonPage<TAdmin>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                 @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
                                                 @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page page = adminService.getUserPage(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));
    }


    @ApiOperation(value = "登录以后返回token")
    @PostMapping("/login")
    public CommonResult login(@RequestBody UmsAdminLoginParam umsAdminLoginParam, BindingResult result) {
        String token = adminService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("登录失败");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public CommonResult<TAdmin> register(@Validated @RequestBody TAdmin admin, BindingResult result) {
        List<FieldError> fieldErrors = result.getFieldErrors();
        if(!fieldErrors.isEmpty()){
            return CommonResult.failed(fieldErrors.get(0).getDefaultMessage());
        }
        adminService.register(admin);
        if(admin == null)
            return CommonResult.failed();
        return CommonResult.success(admin);
    }

    @ApiOperation(value = "获取登录用户信息")
    @GetMapping("/info")
    public CommonResult<TAdmin> info(Principal principal) {
        if (principal == null)
            return CommonResult.unauthorized(null);
        return CommonResult.success(adminService.getAdminByUsername(principal.getName()));
    }

    @ApiOperation(value = "退出")
    @PostMapping("/logout")
    public CommonResult logout() {
        return CommonResult.success(null);
    }


    @ApiOperation("编辑用户")
    @PutMapping("/update")
    public CommonResult update(TAdmin admin) {
        int count = adminService.updateAdmin(admin);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("删除用户")
    @PostMapping("/delete/{id}")
    public CommonResult delete(@PathVariable("id") Long id) {
        int count = adminService.deleteAdmin(id);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation(value = "发布订单")
    @PostMapping("/releaseOrder")
    public CommonResult releaseOrder(@RequestBody TOrder order) {
        int count = adminService.releaseOrder(order);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }
}
