package com.wwinfo.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

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
    private Long assetID;

    @ApiModelProperty(value = "是否黑名单(0->否;1->是)", required = true)
    @NotNull(message = "是否黑名单不能为空")
    private Integer isBlack;

    @ApiModelProperty(value = "黑名单原因(最大长度30)")
    private String blackReason;

    @ApiModelProperty(value = "黑名单设置时间")
    private Date blackTime;

}
