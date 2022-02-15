package com.wwinfo.pojo.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-02-15 14:54
 */
@Data
public class AssetoutregQuery {

    @ApiModelProperty(value = "出库类型(0->使用出库;1->借用出库)")
    private Integer outType;

    @ApiModelProperty(value = "当前状态(0->还未出库;1->已出库;2->已归还)")
    private Integer status;

}
