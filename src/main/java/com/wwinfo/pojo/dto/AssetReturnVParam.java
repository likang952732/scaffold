package com.wwinfo.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-02-15 20:28
 */
@Data
public class AssetReturnVParam {

    @ApiModelProperty(value = "资产id", required = true)
    @NotNull(message = "资产id不能为空")
    private Long assetID;

    @ApiModelProperty(value = "归还时间(yyyy-MM-dd HH:mm:ss)", required = true)
    @NotBlank(message = "归还时间不能为空")
    private String returnTime;

    @ApiModelProperty(value = "备注")
    private String remark;

}
