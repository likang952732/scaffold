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
 * 系统配置
 * </p>
 *
 * @author lk
 * @since 2022-02-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 参数名称
     */
    @ApiModelProperty(value = "参数名称")
    private String name;

    /**
     * 参数键名
     */
    @TableField("fieldName")
    @ApiModelProperty(value = "参数键名")
    private String fieldName;

    /**
     * 参数键值
     */
    @ApiModelProperty(value = "参数键值")
    private String value;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    @TableField("timeAdd")
    private Date timeAdd;


}
