package com.wwinfo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 资产盘点任务表
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Invetorytask implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long ID;

    /**
     * 所属部门ID
     */
    @TableField("orgID")
    private Long orgID;

    /**
     * 计划开始日期
     */
    @TableField("startDate")
    private LocalDate startDate;

    /**
     * 计划结束日期
     */
    @TableField("endDate")
    private Date endDate;

    /**
     * 盘点状态
     */
    private Integer status;

    @TableField("addTime")
    private Date addTime;

    /**
     * 盘点取消原因
     */
    @TableField("cancelReason")
    private String cancelReason;

    /**
     * 结束或取消时间
     */
    @TableField("finishTime")
    private Date finishTime;


}
