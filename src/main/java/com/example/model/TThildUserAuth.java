package com.example.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/*
 @Description
 *@author kang.li
 *@date 2021/4/1 14:36   
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TThildUserAuth {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String phone;

    private String openid;

    private Integer loginType;

    private String sessionKey;

    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
