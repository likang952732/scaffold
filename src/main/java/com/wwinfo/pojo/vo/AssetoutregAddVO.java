package com.wwinfo.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-02-21 10:25
 */
@Data
public class AssetoutregAddVO {

    @ApiModelProperty(value = "资产id", required = true)
    @NotNull(message = "assetID不能为空")
    private Long assetID;

    @ApiModelProperty(value = "出库类型(0->使用出库;1->借用出库)", required = true)
    @NotNull(message = "outType不能为空")
    private Integer outType;

    @ApiModelProperty(value = "出库开始时间", required = true)
    @NotNull(message = "lendStart不能为空")
    private Date lendStart;

    @ApiModelProperty(value = "最迟归还时间", required = true)
    @NotNull(message = "lendEnd不能为空")
    private Date lendEnd;

    @ApiModelProperty(value = "出库目的地(最大长度90)")
    @NotBlank(message = "address不能为空")
    private String address;

    @ApiModelProperty(value = "用途(最大长度30)")
    private String usages;

    @ApiModelProperty(value = "借用人(最大长度30)")
    private String borrowMan;

    @ApiModelProperty(value = "借用部门(最大长度30)")
    private String borrowOrg;

    @ApiModelProperty(value = "预计归还时间")
    private Date estimateTime;

    @ApiModelProperty(value = "备注(最大长度90)")
    private String remark;

}
