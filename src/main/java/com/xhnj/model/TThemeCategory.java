package com.xhnj.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.xhnj.annotation.FieldRepeatValidator;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 主题分类表
 * </p>
 *
 * @author lk
 * @since 2021-02-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@FieldRepeatValidator(fields = {"name"}, message = "主题名称不能重复")
public class TThemeCategory extends Model implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 主题名称
     */
    private String name;

    /**
     * 父主题id
     */
    private String parentId;

    /**
     * 是否是父节点(0否; 1是)
     */
    private Integer isParent;

    /**
     * 排序指数(越小越靠前)
     */
    private Integer sort;

    private Date createTime;


}
