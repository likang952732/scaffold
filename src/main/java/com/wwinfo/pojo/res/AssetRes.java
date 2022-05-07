package com.wwinfo.pojo.res;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-02-15 15:57
 */
@Data
public class AssetRes {

    @ApiModelProperty(value = "ID")
    private Long ID;

    @ApiModelProperty(value = "所属部门ID")
    private Long orgID;

    @ApiModelProperty(value = "所属部门名称")
    private String orgName;

    @ApiModelProperty(value = "资产名称")
    private String name;

    @ApiModelProperty(value = "资产编号")
    private String assetNo;

    @ApiModelProperty(value = "使用部门")
    private String useOrg;

    @ApiModelProperty(value = "设备密级")
    private String secLevel;

    @ApiModelProperty(value = "设备大类")
    private String largeClass;

    @ApiModelProperty(value = "设备小类")
    private String littleClass;

    @ApiModelProperty(value = "品牌")
    private String brand;

    @ApiModelProperty(value = "型号")
    private String model;

    @ApiModelProperty(value = "序列号")
    private String serialNo;

    @ApiModelProperty(value = "说明")
    private String description;

    @ApiModelProperty(value = "价格(元为单位)")
    private Integer price;

    @ApiModelProperty(value = "编制日期")
    private Date setDate;

    @ApiModelProperty(value = "责任人")
    private String staff;

    @ApiModelProperty(value = "计量有效期")
    private String period;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "所在库房ID")
    private Long roomID;

    @ApiModelProperty(value = "所在库房名称")
    private String roomName;

    @ApiModelProperty(value = "检测报警(0->不报警;1->允许位置外报警;2->不允许位置报警)")
    private Integer posAlarmType;

    @ApiModelProperty(value = "当前状态(0->内部；1->外部)")
    private Integer curStatus;

    @ApiModelProperty(value = "当前状态产生时间")
    private Date timeStatus;

    @ApiModelProperty(value = "是否异常(0->否;1->是)")
    private Integer isAbnormal;

    @ApiModelProperty(value = "异常原因(1->异常外出;2->进出状态不匹配;3->外出超过天数)")
    private Integer reasonType;

    @ApiModelProperty(value = "外借状态(1->外借)")
    private Integer lendStatus;

    @ApiModelProperty(value = "外借开始时间")
    private Date lendStart;

    @ApiModelProperty(value = "外借结束时间")
    private Date lendEnd;

    @ApiModelProperty(value = "添加时间")
    private Date timeAdd;

    @ApiModelProperty(value = "修改时间")
    private Date timeModify;

    @ApiModelProperty(value = "销毁状态(0->未销毁;1->销毁)")
    private Integer delStatus;

    @ApiModelProperty(value = "销毁时间")
    private Date delTime;

    @ApiModelProperty(value = "销毁原因")
    private String delReason;

    @ApiModelProperty(value = "是否黑名单(0->否;1->是)")
    private Integer isBlack;

    @ApiModelProperty(value = "黑名单原因")
    private String blackReason;

    @ApiModelProperty(value = "黑名单设置时间")
    private Date blackTime;

    @ApiModelProperty(value = "任务id")
    private Long taskID;

    @ApiModelProperty(value = "是否已绑定RFID(0->否; 1->是)")
    private Integer isBand;

    @ApiModelProperty(value = "RFID打印编号")
    private String rfidPrintNo;

    private Integer taskStatus;

    @ApiModelProperty(value = "外出时长")
    private String outDuration;

}
