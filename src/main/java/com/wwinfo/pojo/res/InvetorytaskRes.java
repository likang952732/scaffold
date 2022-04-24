package com.wwinfo.pojo.res;

import cn.hutool.json.JSONArray;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String orgID;

    @ApiModelProperty(value = "所属部门名称")
    private String orgName;

    @JsonIgnore
    private String orgIDName;

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @ApiModelProperty(value = "计划开始日期")
    private Date startDate;

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @ApiModelProperty(value = "计划结束日期")
    private Date endDate;

    @ApiModelProperty(value = "盘点任务说明")
    private String description;

    @ApiModelProperty(value = "盘点状态(0->盘点中;1->盘点结束;2->盘点取消;3->)")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private Date addTime;

    @ApiModelProperty(value = "盘点取消原因")
    private String cancelReason;

    @ApiModelProperty(value = "结束或取消时间")
    private Date finishTime;

    @ApiModelProperty(value = "部门备注")
    private String remark;

    private JSONArray orgArray;

}
