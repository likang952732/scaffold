package com.xhnj.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 首页轮播广告表
 * </p>
 *
 * @author lk
 * @since 2021-02-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class THomeAdvertise implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 状态(0 展示中; 1未开始; 2已结束)
     */
    private Integer status;

    /**
     * 展示开始时间
     */
    private Date showStartTime;

    /**
     * 展示结束时间
     */
    private Date showEndTime;

    /**
     * 链接地址
     */
    private String url;

    /**
     * 是否验证登录(0否; 1是)
     */
    private Integer isVerifyLogin;

    /**
     * 排序(值越小越靠前)
     */
    private Integer sort;

    private Date createTime;


}
