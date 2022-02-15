package com.wwinfo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 资产进出记录表
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Assetrecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 资产ID
     */
    @ApiModelProperty(value = "资产ID")
    @TableField("assetID")
    private Long assetID;

    /**
     * 位置ID
     */
    @ApiModelProperty(value = "位置ID")
    @TableField("posID")
    private Long posID;

    /**
     * 进出方向
     */
    @ApiModelProperty(value = "进出方向(0->进；1->出)")
    private Integer directory;

    /**
     * 进出时间
     */
    @ApiModelProperty(value = "进出时间")
    @TableField("timeAdd")
    private Date timeAdd;

    /**
     * 原状态
     */
    @ApiModelProperty(value = "原状态(0->内部；1->外部)")
    @TableField("preStatus")
    private Integer preStatus;

    /**
     * 原状态时间
     */
    @ApiModelProperty(value = "原状态时间")
    @TableField("timePreStatus")
    private Date timePreStatus;

    /**
     * 原状态保持时间(单位：小时)
     */
    @ApiModelProperty(value = "原状态保持时间(单位：小时)")
    @TableField("statusTimes")
    private Integer statusTimes;

    /**
     * 原状态是否正确
     */
    @ApiModelProperty(value = "原状态是否正确(0->正常；1->原状态不匹配)")
    @TableField("statusMatched")
    private Integer statusMatched;

    /**
     * 是否报警
     */
    @ApiModelProperty(value = "是否报警(0->不报警;1->报警)")
    @TableField("isAlarm")
    private Integer isAlarm;

    /**
     * 报警类型
     */
    @ApiModelProperty(value = "报警类型(1->异常外出;2->进出状态不匹配;4->黑名单;5->销毁资产)")
    @TableField("alarmType")
    private Integer alarmType;

}
