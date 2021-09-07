package com.example.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author lk
 * @since 2021-02-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TUser extends Model implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 登录名
     */
    @TableField(value = "username")
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 姓名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 身份证
     */
    private String idCard;



    /**
     * 密码
     */
    private String password;

    /**
     * 头像
     */
    private String icon;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 昵称
     */
    @TableField(value = "nick_name")
    @NotBlank(message = "昵称不能为空")
    private String nickName;

    /**
     * 手机号
     */
    @TableField(value = "phone")
    @NotBlank(message = "手机号不能为空")
    private String phone;

    /**
     * 备注信息
     */
    private String note;

    /**
     * 是否绑定微信号：0->未绑定；1->已绑定
     */
    private Integer isBind;

    /**
     * 是否实名认证：0->否；1->是
     */
    private Integer isReal;

    /**
     * 绑定的微信号
     */
    private String wechatNo;

    private Integer collectNum;

    private Integer integral;

    private String walletBalance;

    private Integer csmIntegral;

    private Integer cumuIntegral;

    private String ownSpec;

    /**
     * 创建时间
     */
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 最后登录时间
     */
    private Date loginTime;

    @TableField(exist = false)
    private Integer isStaff;   // 0->否  1->是

    @TableField(exist = false)
    private String staffName;

    @TableField(exist = false)
    private Integer staffType;

    @TableField(exist = false)
    private Integer cartNum;
}
