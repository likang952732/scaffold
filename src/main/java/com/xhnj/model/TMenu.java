package com.xhnj.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.xhnj.annotation.FieldRepeatValidator;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 菜单
 * </p>
 *
 * @author lk
 * @since 2021-02-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@FieldRepeatValidator(fields = {"name"}, message = "菜单名不能重复")
public class TMenu extends Model implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    /**
     * 父级id
     */
    private Long parentId;

    /**
     * 菜单级数
     */
    private Integer level;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 显示状态：0->显示；1->不显示
     */
    @NotNull(message = "状态不能为空")
    private Integer status;

}
