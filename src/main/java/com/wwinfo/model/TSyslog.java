package com.wwinfo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 系统日志
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TSyslog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "logID", type = IdType.AUTO)
    private Long logID;

    /**
     * 登录用户名称
     */
    @ApiModelProperty(value = "登录用户名称")
    @TableField("userName")
    private String userName;

    @ApiModelProperty(value = "操作时间")
    @TableField("timeOperate")
    private Date timeOperate;

    /**
     * 操作动作
     */
    @ApiModelProperty(value = "操作动作")
    private String operate;

    /**
     * 操作对象类型
     */
    @ApiModelProperty(value = "操作对象类型")
    @TableField("objectType")
    private String objectType;

    /**
     * 操作对象名称
     */
    @ApiModelProperty(value = "操作对象名称")
    @TableField("objectName")
    private String objectName;

    /**
     * 操作描述信息
     */
    @ApiModelProperty(value = "操作描述信息")
    private String description;

}
