package com.wwinfo.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 部门管理员表表
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名称
     */
    @ApiModelProperty(value = "用户名称")
    @TableId("userName")
    private String userName;

    /**
     * 所属部门ID
     */
    @ApiModelProperty(value = "所属部门ID")
    @TableField("orgID")
    private Long orgID;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickname;

    /**
     * 密码
     */
    private String password;

    /**
     * 是否还没有登录过
     */
    @ApiModelProperty(value = "是否还没有登录过(0->没有登录过; 1->已登录过)")
    @TableField("neverLogin")
    private Integer neverLogin;

    /**
     * 最后登录时间
     */
    @ApiModelProperty(value = "最后登录时间")
    @TableField("timeLastLogin")
    private Date timeLastLogin;

    /**
     * 最后登录IP地址
     */
    @ApiModelProperty(value = "最后登录IP地址")
    @TableField("lastLoginIP")
    private String lastLoginIP;

    @ApiModelProperty(value = "创建时间")
    @TableField("timeAdd")
    private Date timeAdd;

    @ApiModelProperty(value = "修改时间")
    @TableField("timeModify")
    private Date timeModify;

    /**
     * 密码错误次数
     */
    @ApiModelProperty(value = "密码错误次数")
    @TableField("errorTimes")
    private Integer errorTimes;

    /**
     * 用户是否锁住
     */
    @ApiModelProperty(value = "用户是否锁住(0->未锁住; 1->已锁住)")
    @TableField("isLocked")
    private Integer isLocked;

    /**
     * 用户锁住时间
     */
    @ApiModelProperty(value = "用户锁住时间")
    @TableField("timeLocked")
    private Date timeLocked;

    /**
     * 电子邮箱
     */
    @ApiModelProperty(value = "电子邮箱")
    private String email;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    @TableField("realName")
    private String realName;

    /**
     * 电话
     */
    @ApiModelProperty(value = "电话")
    private String phone;

    /**
     * 地址
     */
    @ApiModelProperty(value = "地址")
    private String address;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像url")
    @TableField("headUrl")
    private String headUrl;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private String sex;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 是否停用
     */
    @ApiModelProperty(value = "是否停用(0->使用; 1->停用)")
    @TableField("isStop")
    private Integer isStop;

}