package com.example.model;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author lk
 * @since 2021-06-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用餐类型
     */
    private String dinnerName;

    /**
     * 用餐时间
     */
    private String dinnerDate;

    /**
     * 用餐人数
     */
    private String dinnerNum;

    /**
     * 用餐区域
     */
    private String dinnerArea;

    /**
     * 用餐地址
     */
    private String dinnerAddress;

    /**
     * 用餐需求
     */
    private String dinnerDemand;

    /**
     * 其他需求
     */
    private String dinneDeatil;

    /**
     * 关联用户
     */
    private Long adminId;

    @TableField(exist = false)
    private String username;

    /**
     * (0->等待接单状态;1->已接单;2->已完成)
     */
    private Integer status;

    private String dinneDown;

    private String dinnePhone;

    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
