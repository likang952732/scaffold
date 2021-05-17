package com.example.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 厨师
 * </p>
 *
 * @author lk
 * @since 2021-05-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TChef implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long adminId;

    private String name;

    /**
     * 性别(0->男；1->女)
     */
    private Integer sex;


    private String phone;

    private String birthDate;

    /**
     * 厨师称号
     */
    private String title;

    /**
     * 从业时间
     */
    private String jobDate;

    private String specialty;

    /**
     * 擅长菜系
     */
    private String cuisine;

    /**
     * 认证状态(0->认证中；1->已认证)
     */
    private Integer status;

    private String qq;

    private String weixin;

    private String area;

    private Integer isFood;

    private Integer isWare;

    private String jobWeek;

    private String jobStartTime;

    private String jobEndTime;

    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
