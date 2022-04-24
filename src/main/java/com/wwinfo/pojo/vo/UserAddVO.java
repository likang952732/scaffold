package com.wwinfo.pojo.vo;

import com.wwinfo.annotation.Phone;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * @Author: lk
 * DateTime: 2022-02-15 11:49
 */
@Data
public class UserAddVO {

    @ApiModelProperty(value = "用户名称(最大长度32)", required = true)
    @NotBlank(message = "用户名称不能为空")
    private String userName;

    @ApiModelProperty(value = "用户类型(1->系统管理员；2->部门管理员；3->日志管理员)", required = true)
    @NotNull(message = "userType不能为空")
    private Integer userType;

    @ApiModelProperty(value = "所属部门ID", required = true)
    @NotNull(message = "所属部门ID不能为空")
    private Long orgID;

    @ApiModelProperty(value = "昵称(最大长度64)")
    private String nickname;

    @ApiModelProperty(value = "密码", required = true)
    @NotBlank(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "确认密码", required = true)
    @NotBlank(message = "确认密码不能为空")
    private String confirmPwd;

    /**
     * 电子邮箱
     */
    @ApiModelProperty(value = "电子邮箱(最大长度64)")
    @Email
    private String email;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名(最大长度64)")
    private String realName;

    /**
     * 电话
     */
    @ApiModelProperty(value = "电话(最大长度64)")
    @Phone
    private String phone;

    /**
     * 地址
     */
    @ApiModelProperty(value = "地址(最大长度512)")
    private String address;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像url(最大长度512)")
    private String headUrl;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别(最大长度8)")
    private String sex;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注(最大长度128)")
    private String remark;

    /**
     * 是否停用
     */
    @ApiModelProperty(value = "是否停用(0->使用; 1->停用)", required = true)
    @NotNull(message = "isStop不能为空")
    private Integer isStop;

}
