package com.wwinfo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

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
    @TableField("assetID")
    private Long assetID;

    /**
     * 位置ID
     */
    @TableField("posID")
    private Long posID;

    /**
     * 进出方向
     */
    private Integer directory;

    /**
     * 进出时间
     */
    @TableField("timeAdd")
    private Date timeAdd;

    /**
     * 原状态
     */
    @TableField("preStatus")
    private Integer preStatus;

    /**
     * 原状态时间
     */
    @TableField("timePreStatus")
    private Date timePreStatus;

    /**
     * 原状态保持时间(单位：小时)
     */
    @TableField("statusTimes")
    private Integer statusTimes;

    /**
     * 原状态是否正确
     */
    @TableField("statusMatched")
    private Integer statusMatched;

    /**
     * 是否报警
     */
    @TableField("isAlarm")
    private Integer isAlarm;

    /**
     * 报警类型
     */
    @TableField("alarmType")
    private Integer alarmType;

}
