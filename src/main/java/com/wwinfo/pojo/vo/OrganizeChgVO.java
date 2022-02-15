package com.wwinfo.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-02-15 13:38
 */
@Data
public class OrganizeChgVO {

    @ApiModelProperty(value = "部门ID")
    @NotNull(message = "orgID不能为空")
    private Long orgID;

    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称", required = true)
    @NotBlank(message = "部门名称不能为空")
    private String orgName;

    /**
     * 说明
     */
    @ApiModelProperty(value = "说明")
    private String remark;

    /**
     * 级别
     */
    @ApiModelProperty(value = "级别(0为顶级)", required = true)
    @NotNull(message = "级别不能为空")
    private Integer level;

    /**
     * 上一级别的orgID
     */
    @ApiModelProperty(value = "上一级别的orgID")
    private Long upOrgID;

    /**
     * 上级ID的链
     */
    @ApiModelProperty(value = "上级ID的链")
    private String chainID;

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
