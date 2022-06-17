package com.wwinfo.pojo.res;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-04-25 11:26
 */
@Data
public class InvetoryRes {

    @ApiModelProperty(value = "任务ID")
    private Long taskID;

    @ApiModelProperty(value = "部门名称")
    private String orgName;

    @ApiModelProperty(value = "资产ID")
    private Long assetID;

    @ApiModelProperty(value = "资产名称")
    private String assetName;

    private Long shouldRoomID;

    @ApiModelProperty(value = "库房名称")
    private String roomName;

    @ApiModelProperty(value = "盘点结果确认(0->未确认;1->确认)")
    private Integer resultCheck;

}