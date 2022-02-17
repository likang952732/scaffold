package com.wwinfo.controller;


import com.wwinfo.annotation.MyLog;
import com.wwinfo.common.CommonPage;
import com.wwinfo.common.CommonResult;
import com.wwinfo.model.User;
import com.wwinfo.pojo.dto.UserLoginParam;
import com.wwinfo.pojo.dto.UserChgParam;
import com.wwinfo.pojo.dto.UserChgpwdParam;
import com.wwinfo.pojo.query.UserQuery;
import com.wwinfo.pojo.vo.UserAddVO;
import com.wwinfo.service.TAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

/**
 * <p>
 *  后台用户管理
 * </p>
 *
 * @author lk
 * @since 2021-02-24
 */
@Api(value = "用户管理接口", tags = "用户管理接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private TAdminService adminService;

    @ApiOperation(value = "分页获取用户")
    @PostMapping("/page")
    public CommonResult<CommonPage<User>> page(@RequestBody UserQuery userQuery) {
      /*  Page page = adminService.getUserPage(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));*/

        return null;
    }

    @ApiOperation(value = "分页获取部门用户")
    @PostMapping("/page/{orgID}")
    public CommonResult<CommonPage<User>> page(@ApiParam(name="orgID",value="部门ID",required=true)
                                                   @PathVariable("orgID")Long orgID,
                                               @RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize,
                                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
      /*  Page page = adminService.getUserPage(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));*/

        return null;
    }


    @ApiOperation(value = "登录以后返回token")
    @PostMapping("/login")
    public CommonResult login(@RequestBody UserLoginParam userLoginParam, BindingResult result) {
       /* List<FieldError> fieldErrors = result.getFieldErrors();
        if(!fieldErrors.isEmpty()){
            return CommonResult.failed(fieldErrors.get(0).getDefaultMessage());
        }
        String token = adminService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);*/

        return null;
    }


    @ApiOperation(value = "获取登录用户信息")
    @PostMapping("/info")
    public CommonResult<User> info(Principal principal) {
       /* if (principal == null)
            return CommonResult.unauthorized(null);
        return CommonResult.success(adminService.getAdminByUsername(principal.getName()));*/

        return null;
    }

    @ApiOperation(value = "退出")
    @PostMapping("/logout")
    public CommonResult logout() {
        return CommonResult.success(null);
    }

    @ApiOperation(value = "添加用户")
    @PostMapping("/add")
    @MyLog(operate = "添加", objectType = "系统权限管理", objectName = "用户管理", descript = "添加用户: #{#admin.username}")
    public CommonResult insert(@RequestBody UserAddVO userAddVO, BindingResult result){
      /*  int count=adminService.insertAdmin(admin);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();*/
        return null;
    }

    @ApiOperation("编辑用户")
    @PostMapping("/update")
    public CommonResult update(@RequestBody UserChgParam userChgParam, BindingResult result) {
       /* int count = adminService.updateAdmin(admin);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();*/

        return null;
    }

    @ApiOperation("删除用户")
    @PostMapping("/{username}")
    public CommonResult delete(@ApiParam(name="username",value="用户登录名称",required=true)@PathVariable("username") String username) {
        /*int count = adminService.deleteAdmin(id);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();*/

        return null;
    }

    @ApiOperation("修改密码")
    @PostMapping("/updatepwd")
    @MyLog(operate = "修改", objectType = "系统权限管理", objectName = "用户管理", descript = "修改用户密码")
    public CommonResult updatePass(@RequestBody UserChgpwdParam userChgpwdParam, BindingResult result) {
     /*   int count = adminService.updatePass(UmsAdminUpdatePassParam);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();*/
        return null;
    }

    @ApiOperation("管理员重置密码")
    @PostMapping("/resetpwd")
    @MyLog(operate = "修改", objectType = "系统权限管理", objectName = "用户管理", descript = "管理员重置密码")
    public CommonResult resetPass() {
       /* int count = adminService.resetPass(admin);
        if(count > 0)
            return CommonResult.success("重制密码成功，密码为 Passwd!23，请稍后自行修改密码");
        return CommonResult.failed();*/
        return null;
    }

}
