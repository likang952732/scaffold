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
 * 所有报警记录
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Alarm implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 报警内容
     */
    @ApiModelProperty(value = "报警内容")
    private String content;

    /**
     * 报警级别
     */
    @ApiModelProperty(value = "报警级别")
    private Integer level;

    /**
     * 报警产生时间
     */
    @ApiModelProperty(value = "报警产生时间")
    @TableField("timeAdd")
    private Date timeAdd;

    /**
     * 报警类型
     */
    @ApiModelProperty(value = "报警类型(0->阅读器断开;1->资产进出报警;2->资产外出超时报警)")
    @TableField("alarmType")
    private Integer alarmType;

    /**
     * 对应源记录ID
     */
    @ApiModelProperty(value = "源记录ID")
    @TableField("sourceID")
    private Long sourceID;

    /**
     * 是否邮件报警
     */
    @ApiModelProperty(value = "是否邮件报警(0->否;1->是)")
    @TableField("alarmEmail")
    private Integer alarmEmail;

    /**
     * 发送是否成功
     */
    @ApiModelProperty(value = "发送是否成功(0->否;1->是)")
    @TableField("isSend")
    private Integer isSend;

    /**
     * 接收邮箱
     */
    @ApiModelProperty(value = "接收邮箱")
    private String email;

}
