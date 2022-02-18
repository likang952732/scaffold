package com.wwinfo.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description: 盘点任务取消
 *
 * @Author: lk
 * DateTime: 2022-02-16 10:16
 */
@Data
public class InvTaskCancelParam {

    @ApiModelProperty(value = "任务id", required = true)
    @NotNull(message = "id不能为空")
    private Long id;

    @ApiModelProperty(value = "盘点状态", required = true)
    @NotNull(message = "status不能为空")
    private Integer status;

    @ApiModelProperty(value = "盘点取消原因(最大长度90)", required = true)
    @NotBlank(message = "cancelReason不能为空")
    private String cancelReason;

    @ApiModelProperty(value = "盘点取消时间", required = true)
    @NotNull(message = "finishTime不能为空")
    private Date finishTime;

}
