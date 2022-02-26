package com.wwinfo.pojo.res;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-02-26 20:23
 */
@Data
public class AssetApiRes {

    @ApiModelProperty(value = "ID")
    private Long ID;

    @ApiModelProperty(value = "资产名称")
    private String name;

    @ApiModelProperty(value = "资产编号")
    private String assetNo;

    @ApiModelProperty(value = "品牌")
    private String brand;

    @ApiModelProperty(value = "型号")
    private String model;

    @ApiModelProperty(value = "所属部门ID")
    private Long orgID;

    @ApiModelProperty(value = "所属部门名称")
    private String orgName;

    @ApiModelProperty(value = "备注")
    private String remark;

    private List<String> rfids;

}
