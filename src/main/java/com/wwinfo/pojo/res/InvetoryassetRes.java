package com.wwinfo.pojo.res;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-02-15 14:35
 */
@Data
public class InvetoryassetRes {

    @ApiModelProperty(value = "任务ID")
    private Long taskID;

    @ApiModelProperty(value = "部门名称")
    private String orgName;

    @ApiModelProperty(value = "资产ID")
    private Long assetID;

    @ApiModelProperty(value = "资产名称")
    private String assetName;

    @ApiModelProperty(value = "资产编号")
    private String assetNo;

    @ApiModelProperty(value = "实际所在库房ID")
    private Long actualRoomID;

    @ApiModelProperty(value = "盘点器ID")
    private String machineID;

    @ApiModelProperty(value = "实际所在库房名称")
    private String actualRoomName;

    @ApiModelProperty(value = "所在库房名称")
    private String roomName;

    @ApiModelProperty(value = "责任人")
    private String staff;

    @ApiModelProperty(value = "盘点时间")
    private Date checkTime;

    @ApiModelProperty(value = "盘点结果类型(0->吻合;1->此资产应该在另一个库房;2->此资产应该在外面;3->未盘点")
    private Integer checkResult;

    @ApiModelProperty(value = "应该在的库房ID")
    private Long shouldRoomID;

    @ApiModelProperty(value = "应该在的库房名称")
    private String shouldRoomName;

    @ApiModelProperty(value = "盘点结果确认(0->未确认;1->确认)")
    private Integer resultCheck;

    @JsonIgnore
    private Integer curStatus;

}
