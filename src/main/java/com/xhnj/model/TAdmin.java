package com.xhnj.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.xhnj.annotation.FieldRepeatValidator;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 后台管理用户
 * </p>
 *
 * @author lk
 * @since 2021-02-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@FieldRepeatValidator(fields = {"username"}, message = "用户名不能重复")
public class TAdmin extends Model implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", required = true)
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", required = true)
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 头像
     */
    private String icon;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    @Email(message = "邮箱格式不合法")
    private String email;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 备注信息
     */
    private String note;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后登录时间
     */
    private Date loginTime;

    /**
     * 帐号启用状态：0->禁用；1->启用
     */
    @ApiModelProperty(value = "状态", required = true)
    @NotNull(message = "状态不能为空")
    private Integer status;


}
