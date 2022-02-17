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
 * RFID阅读记
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Rfidrecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long ID;

    /**
     * RFID编号
     */
    @ApiModelProperty(value = "RFID编号")
    @TableField("rfidNo")
    private String rfidNo;

    /**
     * 阅读器ID
     */
    @ApiModelProperty(value = "阅读器ID")
    @TableField("readerID")
    private Long readerID;

    /**
     * 读到时间
     */
    @ApiModelProperty(value = "读到时间")
    @TableField("timeAdd")
    private Date timeAdd;

    /**
     * 进出计算结果
     */
    @ApiModelProperty(value = "进出计算结果(0->计算中;1->确认为进；2->确认为出；3->忽略)")
    private Integer status;

    /**
     * 是否绑定了资产
     */
    @ApiModelProperty(value = "是否绑定了资产(1->绑定)")
    @TableField("isAsset")
    private Integer isAsset;

    /**
     * 资产ID
     */
    @ApiModelProperty(value = "资产ID")
    @TableField("assetID")
    private Long assetID;

    /**
     * 是否外部RFID
     */
    @ApiModelProperty(value = "是否外部RFID(0->不是外部的RFID;1->外部的RFID)")
    @TableField("isOther")
    private Integer isOther;

}
