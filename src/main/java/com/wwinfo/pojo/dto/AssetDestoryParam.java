package com.wwinfo.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-02-15 17:11
 */
@Data
public class AssetDestoryParam {

    @ApiModelProperty(value = "资产id(逗号分隔)", required = true)
    @NotNull(message = "ids不能为空")
    private String ids;

    @ApiModelProperty(value = "当前状态", required = true)
    @NotNull(message = "当前状态(0->还未出库;1->已出库;2->已归还)")
    private Integer status;

    @ApiModelProperty(value = "销毁原因(最大长度30)", required = true)
    @NotNull(message = "销毁原因不能为空")
    private String delReason;

    @ApiModelProperty(value = "销毁时间", required = true)
    @NotNull(message = "销毁时间不能为空")
    private Date delTime;

}
