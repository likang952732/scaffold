package com.wwinfo.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wwinfo.annotation.MyLog;
import com.wwinfo.common.CommonPage;
import com.wwinfo.common.CommonResult;
import com.wwinfo.model.User;
import com.wwinfo.pojo.dto.UserLoginParam;
import com.wwinfo.pojo.dto.UserChgParam;
import com.wwinfo.pojo.dto.UserChgpwdParam;
import com.wwinfo.pojo.query.UserQuery;
import com.wwinfo.pojo.vo.UserAddVO;
import com.wwinfo.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

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
    private UserService userService;


    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "分页获取用户")
    @PostMapping("/page")
    public CommonResult<CommonPage<User>> page(UserQuery userQuery) {
        IPage page = userService.getUserPage(userQuery);
        return CommonResult.success(CommonPage.restPage(page));
    }


    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "分页获取部门用户")
    @PostMapping("/page/{orgID}")
    public CommonResult<CommonPage<User>> page(@ApiParam(name="orgID",value="部门ID",required=true)
                                                   @PathVariable("orgID")Long orgID,
                                               @RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize,
                                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        IPage page = userService.getPageByOrgID(orgID, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));
    }


    @ApiOperation(value = "登录以后返回token")
    @PostMapping("/login")
    public CommonResult login(UserLoginParam userLoginParam, BindingResult result) {
       /* List<FieldError> fieldErrors = result.getFieldErrors();
        if(!fieldErrors.isEmpty()){
            return CommonResult.failed(fieldErrors.get(0).getDefaultMessage());
        }
        String token = userService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);*/

        return null;
    }


    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "获取登录用户信息")
    @PostMapping("/info")
    public CommonResult<User> info(Principal principal) {
        if (principal == null)
            return CommonResult.unauthorized(null);
        return CommonResult.success(userService.getUserByUserName(principal.getName()));
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "退出")
    @PostMapping("/logout")
    public CommonResult logout() {
        return CommonResult.success(null);
    }

    @ApiOperation(value = "添加用户")
    @PostMapping("/add")
    @MyLog(operate = "添加", objectType = "系统权限管理", objectName = "用户管理", descript = "添加用户: #{#admin.username}")
    public CommonResult add(@Validated UserAddVO userAddVO, BindingResult result){
        List<FieldError> fieldErrors = result.getFieldErrors();
        if(!fieldErrors.isEmpty()){
            return CommonResult.failed(fieldErrors.get(0).getDefaultMessage());
        }
        int count = userService.add(userAddVO);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }


    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation("编辑用户(用户登录名称不能修改)")
    @PostMapping("/update")
    public CommonResult update(@Validated UserChgParam userChgParam, BindingResult result) {
        List<FieldError> fieldErrors = result.getFieldErrors();
        if(!fieldErrors.isEmpty()){
            return CommonResult.failed(fieldErrors.get(0).getDefaultMessage());
        }
        int count = userService.update(userChgParam);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }


    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation("删除用户")
    @PostMapping("/delete/{userName}")
    public CommonResult delete(@ApiParam(name="userName",value="用户登录名称",required=true)@PathVariable("userName") String userName) {
        int count = userService.delete(userName);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation("修改密码")
    @PostMapping("/updatepwd")
    @MyLog(operate = "修改", objectType = "系统权限管理", objectName = "用户管理", descript = "修改用户密码")
    public CommonResult updatePass(@Validated UserChgpwdParam userChgpwdParam, BindingResult result) {
        List<FieldError> fieldErrors = result.getFieldErrors();
        if(!fieldErrors.isEmpty()){
            return CommonResult.failed(fieldErrors.get(0).getDefaultMessage());
        }
        int count = userService.updatePass(userChgpwdParam);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation("管理员重置密码")
    @PostMapping("/resetpwd/{userName}")
    @MyLog(operate = "修改", objectType = "系统权限管理", objectName = "用户管理", descript = "管理员重置密码")
    public CommonResult resetPass(@ApiParam(name="userName",value="用户登录名称",required=true)@PathVariable("userName") String userName) {
        int count = userService.resetPass(userName);
        if(count > 0)
            return CommonResult.success("重制密码成功，密码为 Passwd!23，请稍后自行修改密码");
        return CommonResult.failed();
    }

}
