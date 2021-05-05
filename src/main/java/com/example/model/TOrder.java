package com.example.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author lk
 * @since 2021-05-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单id
     */
    private String orderId;

    /**
     * 用户id
     */
    private Long adminId;

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 订单金额(分为单位)
     */
    private Long amount;

    /**
     * (0->等待接单状态;1->已接单;2->已完成)
     */
    private Integer status;

    private Date createTime;

    /**
     * 订单接收时间
     */
    private Date receiveTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
