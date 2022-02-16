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
 * RFID阅读器表
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Rfidreader implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long ID;

    /**
     * 阅读器名称
     */
    @TableField("readerName")
    @ApiModelProperty(value = "阅读器名称")
    private String readerName;

    /**
     * 阅读器IP地址
     */
    @TableField("readerIP")
    @ApiModelProperty(value = "阅读器IP地址")
    private String readerIP;

    /**
     * 所在位置
     */
    @ApiModelProperty(value = "所在位置")
    private String address;

    /**
     * 品牌
     */
    @ApiModelProperty(value = "品牌")
    private String brand;

    /**
     * 设备型号
     */
    @ApiModelProperty(value = "设备型号")
    private String model;

    /**
     * 接口分类
     */
    @TableField("interfaceType")
    @ApiModelProperty(value = "接口分类(0->有源（M5); 1->有源（自有); 2->无源（1天线); 3->无源（2天线); 4->无源（3天线) 5->无源（4天线）)")
    private Integer interfaceType;

    /**
     * 是否连接报警器
     */
    @TableField("hasAlarm")
    @ApiModelProperty(value = "是否连接报警器(0->否;1->是)")
    private Integer hasAlarm;

    /**
     * 安装日期
     */
    @TableField("setupDate")
    @ApiModelProperty(value = "安装日期")
    private Date setupDate;

    @TableField("timeAdd")
    @ApiModelProperty(value = "加入时间")
    private Date timeAdd;

    /**
     * 最后在线检测时间
     */
    @TableField("checkTime")
    @ApiModelProperty(value = "最后在线检测时间")
    private Date checkTime;

    /**
     * 最后在线状态
     */
    @TableField("lastStatus")
    @ApiModelProperty(value = "最后在线状态(0->在线；1->不在线)")
    private Integer lastStatus;

    /**
     * 断线是否报警
     */
    @TableField("isAlarm")
    @ApiModelProperty(value = "断线是否报警(0->否;1->是)")
    private Integer isAlarm;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;


}
