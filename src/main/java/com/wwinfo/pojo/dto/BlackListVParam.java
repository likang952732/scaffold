package com.wwinfo.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-02-15 20:59
 */@Data
public class BlackListVParam {

    @ApiModelProperty(value = "资产id", required = true)
    @NotNull(message = "资产id不能为空")
    private String ids;

    @ApiModelProperty(value = "设置动作(0->取消黑名单;1->设置黑名单)", required = true)
    @NotNull(message = "setType不能为空")
    private Integer setType;

    @ApiModelProperty(value = "黑名单原因(最大长度30)")
    private String blackReason;

}
