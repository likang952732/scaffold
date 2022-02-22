package com.wwinfo.pojo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 登录参数
 */
@Data
public class UserLoginParam {

    @ApiModelProperty(value = "用户名(最大长度32)", required = true)
    @NotBlank(message = "用户名不能为空")
    private String userName;

    @ApiModelProperty(value = "密码", required = true)
    @NotBlank(message = "密码不能为空用户名")
    private String password;

    @ApiModelProperty(value = "验证码(通过验证码接口获取base64验证码)", required = true)
    @NotBlank(message = "验证码不能为空")
    private String code;

    @ApiModelProperty(hidden=true)
    @JsonIgnore
    private String clientIP;

}
