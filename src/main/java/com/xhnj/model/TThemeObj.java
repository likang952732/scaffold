package com.xhnj.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import com.xhnj.annotation.FieldRepeatValidator;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 主题分类对象
 * </p>
 *
 * @author lk
 * @since 2021-02-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@FieldRepeatValidator(fields = {"name"}, message = "分类对象名称不能重复")
public class TThemeObj implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 主题id
     */
    private Long themeId;

    /**
     * 对象名称
     */
    private String name;

    /**
     * 上传图片地址，以逗号隔开
     */
    private String pics;

    /**
     * 介绍
     */
    private String introduce;

    /**
     * 详情
     */
    private String detail;

    /**
     * 分类类型(1->景区;2->周边服务)
     */
    private Integer type;

    /**
     * 排序指数(越小越靠前)
     */
    private Integer sort;

    /**
     * 启用状态：0->启用；1->不启用
     */
    @NotNull(message = "状态不能为空")
    private Integer status;

    private Date createTime;

}
