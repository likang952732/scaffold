package com.wwinfo.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-02-15 20:18
 */
@Data
public class AssetOutRegVO {

    @ApiModelProperty(value = "资产ID", required = true)
    @NotNull(message = "资产ID不能为空")
    private Long assetID;

    @ApiModelProperty(value = "登记时间(yyyy-MM-dd HH:mm:ss)", required = true)
    @NotNull(message = "登记时间不能为空")
    private String regTime;

    @ApiModelProperty(value = "出库类型", required = true)
    @NotNull(message = "出库类型不能为空")
    private Integer outType;

    @ApiModelProperty(value = "出库开始时间(yyyy-MM-dd HH:mm:ss)", required = true)
    @NotNull(message = "出库开始时间不能为空")
    private String lendStart;

    @ApiModelProperty(value = "最迟结束时间(yyyy-MM-dd HH:mm:ss)", required = true)
    @NotNull(message = "最迟结束时间不能为空")
    private String lendEnd;

    @ApiModelProperty(value = "出库目的地(最大长度90)")
    private String address;

    @ApiModelProperty(value = "用途(最大长度30)")
    private String usages;

    @ApiModelProperty(value = "借用人(最大长度30)")
    private String borrowMan;

    @ApiModelProperty(value = "借用部门(最大长度30)")
    private String borrowOrg;

    @ApiModelProperty(value = "预计归还时间(yyyy-MM-dd HH:mm:ss)")
    private String estimateTime;

    @ApiModelProperty(value = "备注(最大长度90)")
    private String remark;

}
