package com.wwinfo.model;

import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 资产盘点清单
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Invetoryasset implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("taskID")
    private Long taskID;

    /**
     * 资产ID
     */
    @TableField("assetID")
    private Long assetID;

    /**
     * 库房ID
     */
    @TableField("roomID")
    private Long roomID;

    /**
     * 盘点时间
     */
    @TableField("checkTime")
    private Date checkTime;

    /**
     * 盘点结果类型
     */
    @TableField("checkResult")
    private Integer checkResult;

    /**
     * 应该在的库房ID
     */
    @TableField("shouldRoomID")
    private Long shouldRoomID;

    /**
     * 盘点结果确认
     */
    @TableField("resultCheck")
    private Integer resultCheck;


}
