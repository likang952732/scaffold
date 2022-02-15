package com.wwinfo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 库房表
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 库房名称
     */
    @TableField("roomName")
    private String roomName;

    /**
     * 位置描述
     */
    private String address;

    /**
     * 库房说明
     */
    private String desc;

    /**
     * 库房等级
     */
    private String level;

    /**
     * 责任人
     */
    private String responsible;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 备注
     */
    private String remark;

    @TableField("timeAdd")
    private LocalDateTime timeAdd;


}
