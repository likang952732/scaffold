package com.wwinfo.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-04-15 17:20
 */
@Data
public class PersonnelLibAddVO {

    @ApiModelProperty(value = "部门id", required = true)
    @NotNull(message = "部门id不能为空")
    private Long orgID;

    @ApiModelProperty(value = "部门名称", required = true)
    private String orgName;

    @ApiModelProperty(value = "姓名", required = true)
    @NotBlank(message = "姓名不能为空")
    private String fullName;

    @ApiModelProperty(value = "工号", required = true)
    @NotBlank(message = "工号不能为空")
    private String jobNo;

    @ApiModelProperty(value = "手机号", required = true)
    @NotBlank(message = "手机号不能为空")
    private String phone;

    @ApiModelProperty(value = "备注")
    private String remark;

}
