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
 * RFID进出口设置
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Entrypos implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long ID;

    /**
     * 位置名称
     */
    @TableField("posName")
    private String posName;

    /**
     * 首次读到的阅读器ID(对应表rfidReader)
     */
    @TableField("startReaderID")
    private Long startReaderID;

    /**
     * 最后读到的阅读器ID
     */
    @TableField("endReaderID")
    private Long endReaderID;

    /**
     * 关联库房ID
     */
    @TableField("roomID")
    private Long roomID;

    /**
     * 进出方向
     */
    private Integer directory;

    /**
     * 是否报警
     */
    @TableField("isAlarm")
    private Integer isAlarm;

    /**
     * 备注
     */
    private String remark;

    @TableField("timeAdd")
    private Date timeAdd;

    @TableField("timeModify")
    private Date timeModify;


}
