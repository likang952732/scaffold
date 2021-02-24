package com.xhnj.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xhnj.common.CommonPage;
import com.xhnj.common.CommonResult;
import com.xhnj.common.exception.BusinessException;
import com.xhnj.dto.UmsAdminLoginParam;
import com.xhnj.model.TAdmin;
import com.xhnj.model.TRole;
import com.xhnj.service.ITAdminService;
import com.xhnj.service.ITRoleService;
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
@RestController
@RequestMapping("/admin")
public class TAdminController {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private ITAdminService adminService;
    @Autowired
    private ITRoleService roleService;


    @ApiOperation(value = "登录以后返回token")
    @PostMapping("/login")
    public CommonResult login(UmsAdminLoginParam umsAdminLoginParam, BindingResult result) {
        String token = adminService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
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
    public CommonResult<TAdmin> register(@Validated TAdmin admin, BindingResult result) {
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
    public CommonResult<Map> info(Principal principal) {
        if (principal == null)
            return CommonResult.unauthorized(null);
        TAdmin admin = adminService.getAdminByUsername(principal.getName());
        Long id = admin.getId();
        Map<String, Object> data = new HashMap<>();
        data.put("username", admin.getUsername());
        //data.put("roles", roleService.getUmsRole(id));
        data.put("icon", admin.getIcon());
        //data.put("menus", roleService.getMenuList(id));
        return CommonResult.success(data);
    }

    @ApiOperation(value = "退出")
    @PostMapping("/logout")
    public CommonResult logout() {
        return CommonResult.success(null);
    }

    @ApiOperation(value = "分页获取用户列表数据")
    @GetMapping("/list")
    public CommonResult<CommonPage<TAdmin>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                 @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
                                                 @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page page = adminService.getUserPage(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));
    }

    /*@ApiOperation("修改用户状态")
    @PostMapping("/updateStatus/{id}")
    public CommonResult updateStatus(@PathVariable("id") Long id, @RequestParam("status") Integer status) {
        TAdmin admin = new TAdmin();
        admin.setStatus(status);
        int count = adminService.updateAdmin(id, admin);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }*/

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

    @ApiOperation("获取指定用户的角色")
    @GetMapping("/role/{id}")
    public CommonResult<List<TRole>> role(@PathVariable("id") Long id) {
        List<TRole> roles = roleService.getUmsRole(id);
        return CommonResult.success(roles);
    }

    @ApiOperation("分配角色")
    @PostMapping("/role/update")
    public CommonResult allocRole(@RequestParam("userId") Long userId, @RequestParam("roleIds")List<Long> roleIds) {
        int count = adminService.updateRole(userId, roleIds);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

}
