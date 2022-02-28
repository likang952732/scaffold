package com.wwinfo.pojo.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-02-15 16:14
 */
@Data
public class AssetQuery {

    @ApiModelProperty(value = "资产名称")
    private String name;

    @ApiModelProperty(value = "资产编号")
    private String assetNo;

    @ApiModelProperty(value = "当前状态(0->内部；1->外部)")
    private Integer curStatus;

    @ApiModelProperty(value = "是否异常(0->否;1->是)")
    private Integer isAbnormal;

    @ApiModelProperty(value = "外借状态(1->外借)")
    private Integer lendStatus;

    @ApiModelProperty(value = "销毁状态(0->未销毁;1->销毁)")
    private Integer delStatus;

    @ApiModelProperty(value = "是否黑名单(0->否;1->是)")
    private Integer isBlack;

    @ApiModelProperty(value = "每页显示条数(默认为10条)")
    private Integer pageSize = 10;

    @ApiModelProperty(value = "页码")
    private Integer pageNum = 1;


}
