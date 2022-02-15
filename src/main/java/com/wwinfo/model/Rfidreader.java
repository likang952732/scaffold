package com.wwinfo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
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
    private Long id;

    /**
     * 阅读器名称
     */
    @TableField("readerName")
    private String readerName;

    /**
     * 阅读器IP地址
     */
    @TableField("readerIP")
    private String readerIP;

    /**
     * 所在位置
     */
    private String address;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 设备型号
     */
    private String model;

    /**
     * 接口分类
     */
    @TableField("interfaceType")
    private Integer interfaceType;

    /**
     * 是否连接报警器
     */
    @TableField("hasAlarm")
    private Integer hasAlarm;

    /**
     * 安装日期
     */
    @TableField("setupDate")
    private LocalDate setupDate;

    @TableField("timeAdd")
    private LocalDateTime timeAdd;

    /**
     * 最后在线检测时间
     */
    @TableField("checkTime")
    private LocalDateTime checkTime;

    /**
     * 最后在线状态
     */
    @TableField("lastStatus")
    private Integer lastStatus;

    /**
     * 断线是否报警
     */
    @TableField("isAlarm")
    private Integer isAlarm;

    /**
     * 备注
     */
    private String remark;


}
