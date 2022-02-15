package com.wwinfo.pojo.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-02-15 12:42
 */
@Data
public class OrganizeQuery {

    @ApiModelProperty(value = "部门名称")
    private String orgName;

    @ApiModelProperty(value = "部门负责人")
    private String manager;

    @ApiModelProperty(value = "负责人手机号")
    private String mobile;

}


