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
 * 资产出库登记表
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Assetoutreg implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long ID;

    /**
     * 资产ID
     */
    @TableField("assetID")
    private Long assetID;

    /**
     * 登记时间
     */
    @TableField("regTime")
    private Date regTime;

    /**
     * 出库类型
     */
    @TableField("outType")
    private Integer outType;

    /**
     * 出库开始时间
     */
    @TableField("lendStart")
    private Date lendStart;

    /**
     * 最迟结束时间
     */
    @TableField("lendEnd")
    private Date lendEnd;

    /**
     * 出库目的地
     */
    private String address;

    /**
     * 用途
     */
    private String usages;

    /**
     * 借用人
     */
    @TableField("borrowMan")
    private String borrowMan;

    /**
     * 借用部门
     */
    @TableField("borrowOrg")
    private String borrowOrg;

    /**
     * 预计归还时间
     */
    @TableField("estimateTime")
    private Date estimateTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 实际出库检测到时间
     */
    @TableField("outTime")
    private Date outTime;

    /**
     * 实际入库检测到时间
     */
    @TableField("inTime")
    private Date inTime;

    /**
     * 当前状态
     */
    private Integer status;

    /**
     * 归还时间
     */
    @TableField("returnTime")
    private Date returnTime;


}
