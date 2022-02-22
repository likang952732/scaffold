package com.wwinfo.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description: 盘点任务结束或取消
 *
 * @Author: lk
 * DateTime: 2022-02-16 10:16
 */
@Data
public class InvTaskCancelParam {

    @ApiModelProperty(value = "任务id", required = true)
    @NotNull(message = "id不能为空")
    private Long id;

    @ApiModelProperty(value = "类型(1->盘点结束;2->盘点取消)", required = true)
    @NotNull(message = "type不能为空")
    private Integer type;

    @ApiModelProperty(value = "盘点取消原因(最大长度90)", required = true)
    @NotBlank(message = "cancelReason不能为空")
    private String cancelReason;

}
