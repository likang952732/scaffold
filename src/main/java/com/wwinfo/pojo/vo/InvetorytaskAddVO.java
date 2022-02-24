package com.wwinfo.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * Description: 资产盘点任务
 *
 * @Author: lk
 * DateTime: 2022-02-16 9:57
 */
@Data
public class InvetorytaskAddVO {

    @ApiModelProperty(value = "所属部门ID", required = true)
    @NotNull(message = "orgID不能为空")
    private Long orgID;

    @ApiModelProperty(value = "计划开始日期(yyyy-MM-dd)", required = true)
    @NotNull(message = "startDate不能为空")
    private String startDate;

    @ApiModelProperty(value = "计划结束日期(yyyy-MM-dd)", required = true)
    @NotNull(message = "endDate不能为空")
    private String endDate;

    @ApiModelProperty(value = "计划结束日期(最大长度90)")
    private String description;

}
