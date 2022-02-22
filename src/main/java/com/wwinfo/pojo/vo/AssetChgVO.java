package com.wwinfo.pojo.vo;

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
 * DateTime: 2022-02-15 16:59
 */
@Data
public class AssetChgVO {

    @ApiModelProperty(value = "资产id")
    @NotNull(message = "id不能为空")
    private Long id;

    @ApiModelProperty(value = "所属部门ID")
    @NotNull(message = "orgID不能为空")
    private Long orgID;

    @ApiModelProperty(value = "资产名称(最大长度为30)")
    @NotBlank(message = "资产名称不能为空")
    private String name;

    @ApiModelProperty(value = "资产编号(最大长度为32)")
    @NotBlank(message = "RFID编号不能为空")
    private String rfidNo;

    @ApiModelProperty(value = "使用部门(最大长度为30)")
    private String useOrg;

    @ApiModelProperty(value = "设备密级(最大长度为30)")
    private String secLevel;

    @ApiModelProperty(value = "设备大类(最大长度为30)")
    private String largeClass;

    @ApiModelProperty(value = "设备小类(最大长度为30)")
    private String littleClass;

    @ApiModelProperty(value = "品牌(最大长度为30)")
    private String brand;

    @ApiModelProperty(value = "型号(最大长度为30)")
    private String model;

    @ApiModelProperty(value = "序列号(最大长度为32)")
    private String serialNo;

    @ApiModelProperty(value = "说明(最大长度为300)")
    private String desc;

    @ApiModelProperty(value = "价格(元为单位)")
    private Integer price;

    @ApiModelProperty(value = "编制日期")
    private Date setDate;

    @ApiModelProperty(value = "责任人(最大长度为30)")
    private String staff;

    @ApiModelProperty(value = "计量有效期(最大长度为15)")
    private String period;

    @ApiModelProperty(value = "备注(最大长度为300)")
    private String remark;

    @ApiModelProperty(value = "所在库房ID")
    private Long roomID;

    @ApiModelProperty(value = "检测报警(0->不报警;1->允许位置外报警;2->不允许位置报警)")
    private Integer posAlarmType;

    @ApiModelProperty(value = "当前状态(0->内部；1->外部)")
    @NotNull(message = "当前状态不能为空")
    private Integer curStatus;

}
