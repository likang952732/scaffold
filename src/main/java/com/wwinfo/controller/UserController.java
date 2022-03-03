package com.wwinfo.controller;


import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wwinfo.annotation.MyLog;
import com.wwinfo.common.CommonPage;
import com.wwinfo.common.CommonResult;
import com.wwinfo.common.ResultCode;
import com.wwinfo.model.TAdmin;
import com.wwinfo.model.User;
import com.wwinfo.pojo.dto.UserLoginParam;
import com.wwinfo.pojo.dto.UserChgParam;
import com.wwinfo.pojo.dto.UserChgpwdParam;
import com.wwinfo.pojo.query.UserQuery;
import com.wwinfo.pojo.vo.UserAddVO;
import com.wwinfo.pojo.vo.UserRoleVO;
import com.wwinfo.service.TMenuService;
import com.wwinfo.service.TRoleService;
import com.wwinfo.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private TRoleService roleService;

    @Autowired
    private TMenuService menuService;


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
    @MyLog(operate = "登录", objectType = "用户登录", objectName = "用户登录", descript = "用户登录: #{#userLoginParam.userName}")
    public CommonResult login(@Validated UserLoginParam userLoginParam, BindingResult result, HttpServletRequest request) {
        List<FieldError> fieldErrors = result.getFieldErrors();
        if(!fieldErrors.isEmpty()){
            return CommonResult.failed(fieldErrors.get(0).getDefaultMessage());
        }

        //校验验证码
        String sessionCode = String.valueOf(request.getServletContext().getAttribute("JCCODE")).toLowerCase();
        String receivedCode = userLoginParam.getCode().toLowerCase();
        boolean b = !sessionCode.equals("") && !receivedCode.equals("") && sessionCode.equals(receivedCode);
        if(!b){
            return CommonResult.failed(ResultCode.VALIDATE_FAILED,"验证码错误");
        }
        userLoginParam.setClientIP(ServletUtil.getClientIP(request));
        String token = userService.login(userLoginParam);
        if (StrUtil.isBlank(token)){
            return CommonResult.validateFailed("用户名或密码错误");
        }
        if("99".equals(token)){
            return CommonResult.validateFailed("多次密码错误锁住,请稍后再试！");
        }

        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);

        User user = userService.getUserByUserName(userLoginParam.getUserName());
        Integer firstFlag = user.getFirstFlag();
        tokenMap.put("firstFlag", firstFlag);
        if("0".equals(firstFlag.toString())){
            return CommonResult.success(tokenMap,"用户为首次登陆,请重新修改密码");
        }
        return CommonResult.success(tokenMap);
    }


    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "获取登录用户信息")
    @PostMapping("/info")
    public CommonResult info(Principal principal) {
        if (principal == null)
            return CommonResult.unauthorized(null);
        User user = userService.getUserByUserName(principal.getName());
        Long id = user.getId();
        Map<String, Object> data = new HashMap<>();
        data.put("username", user.getUserName());
        data.put("userType", user.getUserType());
        data.put("nikeName", user.getNickname());
        data.put("roles", roleService.getRoleByUserId(id));
        data.put("menus", menuService.getMenuByUserId(id));
        return CommonResult.success(data);
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "退出")
    @PostMapping("/logout")
    public CommonResult logout() {
        return CommonResult.success(null);
    }

    @ApiOperation(value = "添加用户")
    @PostMapping("/add")
    @MyLog(operate = "添加", objectType = "系统权限管理", objectName = "用户管理", descript = "添加用户: #{#userAddVO.userName}")
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
    @ApiOperation("编辑用户")
    @PostMapping("/update")
    @MyLog(operate = "编辑", objectType = "系统权限管理", objectName = "用户管理", descript = "编辑用户: #{#userChgParam.userName}")
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
    @PostMapping("/delete/{id}")
    @MyLog(operate = "删除", objectType = "系统权限管理", objectName = "用户管理", descript = "删除用户: #{#userName}")
    public CommonResult delete(@ApiParam(name="id",value="用户id",required=true)@PathVariable("id") Long id) {
        int count = userService.delete(id);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation("修改密码")
    @PostMapping("/updatepwd")
    @MyLog(operate = "修改", objectType = "系统权限管理", objectName = "用户管理", descript = "修改用户密码: #{#userChgpwdParam.userName}")
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
    @PostMapping("/resetpwd/{id}")
    @MyLog(operate = "修改", objectType = "系统权限管理", objectName = "用户管理", descript = "管理员重置密码: #{#userName}")
    public CommonResult resetPass(@ApiParam(name="id",value="用户id",required=true)@PathVariable("id") Long id) {
        int count = userService.resetPass(id);
        if(count > 0)
            return CommonResult.success("重制密码成功，密码为 Passwd!23，请稍后自行修改密码");
        return CommonResult.failed();
    }

    @ApiOperation("分配角色")
    @PostMapping("/updaterole")
    @MyLog(operate = "修改", objectType = "系统权限管理", objectName = "角色管理", descript = "用户角色分配")
    public CommonResult updateRole(@ApiParam(name="userId",value="用户id",required=true)@RequestParam("userId") Long userId,
                                   @ApiParam(name="roleIds",value="角色id列表(多个用逗号分隔)",required=true)@RequestParam("roleIds")List<Long> roleIds) {
        int count = userService.updateRole(userId, roleIds);
        if(count > 0)
            return CommonResult.success(null);
        return CommonResult.failed();
    }

}
