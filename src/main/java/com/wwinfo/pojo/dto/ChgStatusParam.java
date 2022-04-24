package com.wwinfo.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-02-15 15:15
 */
@Data
public class ChgStatusParam {

    @ApiModelProperty(value = "id", required = true)
    @NotNull(message = "id不能为空")
    private Long id;

    @ApiModelProperty(value = "借用部门")
    private String borrowOrg;

    @ApiModelProperty(value = "借用人")
    private String borrowMan;

    @ApiModelProperty(value = "用途")
    private String usages;

    @ApiModelProperty(value = "备注")
    private String remark;

}
