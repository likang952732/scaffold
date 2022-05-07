package com.wwinfo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 异常读取记录表
 * </p>
 *
 * @author lk
 * @since 2022-05-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AbnoReadRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 资产id
     */
    @TableField("assetID")
    private Long assetID;

    /**
     * 阅读器名称
     */
    @TableField("readerName")
    private String readerName;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;


}
