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
 * 部门表
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Organize implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "部门ID")
    @TableId(value = "orgID", type = IdType.AUTO)
    private Long orgID;

    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称")
    @TableField("orgName")
    private String orgName;

    /**
     * 说明
     */
    @ApiModelProperty(value = "说明")
    private String remark;

    /**
     * 级别
     */
    @ApiModelProperty(value = "级别(0为顶级)")
    private Integer level;

    /**
     * 上一级别的orgID
     */
    @ApiModelProperty(value = "上一级别的orgID")
    @TableField("upOrgID")
    private Long upOrgID;

    /**
     * 上级ID的链
     */
    @ApiModelProperty(value = "上级ID的链")
    @TableField("chainID")
    private String chainID;

    @ApiModelProperty(value = "创建时间")
    @TableField("timeAdd")
    private Date timeAdd;

    @ApiModelProperty(value = "修改时间")
    @TableField("timeModify")
    private Date timeModify;

    /**
     * 部门负责人
     */
    @ApiModelProperty(value = "部门负责人")
    private String manager;

    /**
     * 电话
     */
    @ApiModelProperty(value = "电话")
    private String phone;

    /**
     * 传真
     */
    @ApiModelProperty(value = "传真")
    private String fax;

    /**
     * 负责人手机号
     */
    @ApiModelProperty(value = "负责人手机号")
    private String mobile;

}
