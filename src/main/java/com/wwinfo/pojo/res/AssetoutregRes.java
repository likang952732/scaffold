package com.wwinfo.pojo.res;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-02-15 14:57
 */
@Data
public class AssetoutregRes {

    private Long ID;

    @ApiModelProperty(value = "资产ID")
    private Long assetID;

    @ApiModelProperty(value = "资产名称")
    private String assetName;

    @ApiModelProperty(value = "登记时间")
    private Date regTime;

    @ApiModelProperty(value = "出库类型(0->使用出库;1->借用出库)")
    private Integer outType;

    @ApiModelProperty(value = "出库开始时间")
    private Date lendStart;

    @ApiModelProperty(value = "最迟结束时间")
    private Date lendEnd;

    @ApiModelProperty(value = "出库目的地")
    private String address;

    @ApiModelProperty(value = "用途")
    private String usages;

    @ApiModelProperty(value = "用途")
    private String borrowMan;

    @ApiModelProperty(value = "借用部门")
    private String borrowOrg;

    @ApiModelProperty(value = "预计归还时间")
    private Date estimateTime;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "实际出库检测到时间")
    private Date outTime;

    @ApiModelProperty(value = "实际入库检测到时间")
    private Date inTime;

    @ApiModelProperty(value = "当前状态(0->还未出库;1->已出库;2->已归还)")
    private Integer status;

    @ApiModelProperty(value = "归还时间")
    private Date returnTime;

}
