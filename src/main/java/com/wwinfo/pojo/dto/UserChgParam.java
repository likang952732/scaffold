package com.wwinfo.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * Description: 用户编辑
 * @Author: lk
 * DateTime: 2022-02-14 22:25
 */
@Data
public class UserChgParam {

    @ApiModelProperty(value = "用户名称(最大长度32)", required = true)
    @NotBlank(message = "用户名称不能为空")
    private String userName;

    @ApiModelProperty(value = "所属部门ID", required = true)
    @NotNull(message = "orgID不能为空")
    private Long orgID;

    @ApiModelProperty(value = "昵称(最大长度64)")
    private String nickname;

    @ApiModelProperty(value = "电子邮箱(最大长度64)")
    private String email;

    @ApiModelProperty(value = "姓名(最大长度64)")
    private String realName;

    @ApiModelProperty(value = "电话(最大长度64)")
    private String phone;

    @ApiModelProperty(value = "地址(最大长度512)")
    private String address;

    @ApiModelProperty(value = "头像(最大长度512)")
    private String headUrl;

    @ApiModelProperty(value = "性别(最大长度8)")
    private String sex;

    @ApiModelProperty(value = "备注(最大长度128")
    private String remark;

    @ApiModelProperty(value = "是否停用")
    @NotNull(message = "isStop不能为空")
    private Integer isStop;
}
