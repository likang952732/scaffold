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
 * 资产表
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Asset implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long ID;

    /**
     * 所属部门ID
     */
    @TableField("orgID")
    private Long orgID;

    /**
     * 资产名称
     */
    private String name;

    /**
     * 资产编号
     */
    @TableField("assetNo")
    private String assetNo;

    /**
     * RFID编号
     */
    @TableField("rfidNo")
    private String rfidNo;

    /**
     * 使用部门
     */
    @TableField("useOrg")
    private String useOrg;

    /**
     * 设备密级
     */
    @TableField("secLevel")
    private String secLevel;

    /**
     * 设备大类
     */
    @TableField("largeClass")
    private String largeClass;

    /**
     * 设备小类
     */
    @TableField("littleClass")
    private String littleClass;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 型号
     */
    private String model;

    /**
     * 序列号
     */
    @TableField("serialNo")
    private String serialNo;

    /**
     * 说明
     */
    private String desc;

    /**
     * 价格(元为单位)
     */
    private Integer price;

    /**
     * 编制日期
     */
    @TableField("setDate")
    private Date setDate;

    /**
     * 责任人
     */
    private String staff;

    /**
     * 计量有效期
     */
    private String period;

    /**
     * 备用1
     */
    private String reserve1;

    /**
     * 备用2
     */
    private String reserve2;

    /**
     * 备用3
     */
    private String reserve3;

    /**
     * 备注
     */
    private String remark;

    /**
     * 关联库房ID
     */
    @TableField("roomID")
    private Long roomID;

    /**
     * 检测报警
     */
    @TableField("posAlarmType")
    private Integer posAlarmType;

    /**
     * 当前状态
     */
    @TableField("curStatus")
    private Integer curStatus;

    /**
     * 当前状态产生时间
     */
    @TableField("timeStatus")
    private Date timeStatus;

    /**
     * 是否异常
     */
    @TableField("isAbnormal")
    private Integer isAbnormal;

    /**
     * 异常原因
     */
    @TableField("reasonType")
    private Integer reasonType;

    /**
     * 外借状态
     */
    @TableField("lendStatus")
    private Integer lendStatus;

    /**
     * 外借开始时间
     */
    @TableField("lendStart")
    private Date lendStart;

    /**
     * 外借结束时间
     */
    @TableField("lendEnd")
    private Date lendEnd;

    @TableField("timeAdd")
    private Date timeAdd;

    @TableField("timeModify")
    private Date timeModify;

    /**
     * 销毁状态
     */
    @TableField("delStatus")
    private Integer delStatus;

    /**
     * 销毁时间
     */
    @TableField("delTime")
    private Date delTime;

    /**
     * 销毁原因
     */
    @TableField("delReason")
    private String delReason;

    /**
     * 是否黑名单
     */
    @TableField("isBlack")
    private Integer isBlack;

    /**
     * 黑名单原因
     */
    @TableField("blackReason")
    private String blackReason;

    /**
     * 黑名单设置时间
     */
    @TableField("blackTime")
    private Date blackTime;


}
