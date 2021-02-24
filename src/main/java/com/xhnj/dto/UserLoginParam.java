package com.xhnj.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/*
 @Description 小程序登录参数
 *@author kang.li
 *@date 2021/2/23 10:07   
 */
@Data
public class UserLoginParam {
    @ApiModelProperty(value = "登录账号", required = true)
    @NotEmpty(message = "登录账号不能为空")
    private String loginAccount;
    @ApiModelProperty(value = "密码", required = true)
    @NotEmpty(message = "密码不能为空")
    private String password;
}
