package com.wwinfo.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-02-21 10:49
 */
@Data
public class InvetorytaskChgVO {

    @ApiModelProperty(value = "id", required = true)
    @NotNull(message = "id不能为空")
    private Long id;

    @ApiModelProperty(value = "所属部门ID", required = true)
    @NotNull(message = "orgID不能为空")
    private Long orgID;

    @ApiModelProperty(value = "计划开始日期", required = true)
    @NotNull(message = "startDate不能为空")
    private Date startDate;

    @ApiModelProperty(value = "计划结束日期", required = true)
    @NotNull(message = "endDate不能为空")
    private Date endDate;

    @ApiModelProperty(value = "盘点任务说明(最大长度90)")
    private String description;

}
