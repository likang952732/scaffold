package com.example.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/*
 @Description 微信登录参数类
 *@author kang.li
 *@date 2021/4/1 14:14   
 */
@Getter
@Setter
public class WxLoginParam {
    @ApiModelProperty(value = "code", required = true)
    @NotEmpty(message = "code不能为空")
    private String code;

    private String phone;

    private String openId;

    private String encryptedData;

    private String iv;

    private String sessionKey;
}
