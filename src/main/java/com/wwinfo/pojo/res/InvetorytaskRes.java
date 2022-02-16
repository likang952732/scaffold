package com.wwinfo.pojo.res;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-02-15 14:43
 */
@Data
public class InvetorytaskRes {

    @ApiModelProperty(value = "盘点任务ID")
    private Long ID;

    @ApiModelProperty(value = "所属部门ID")
    private Long orgID;

    @ApiModelProperty(value = "所属部门名称")
    private String orgName;

    @ApiModelProperty(value = "计划开始日期")
    private Date startDate;

    @ApiModelProperty(value = "计划结束日期")
    private Date endDate;

    @ApiModelProperty(value = "盘点状态(0->盘点中;1->盘点结束;2->盘点取消)")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private Date addTime;

    @ApiModelProperty(value = "盘点取消原因")
    private String cancelReason;

    @ApiModelProperty(value = "结束或取消时间")
    private Date finishTime;

}
