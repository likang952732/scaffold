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

    @ApiModelProperty(value = "当前状态", required = true)
    @NotNull(message = "当前状态(0->还未出库;1->已出库;2->已归还)")
    private Integer status;

}
