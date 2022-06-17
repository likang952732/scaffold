package com.wwinfo.pojo.res;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-05-09 14:40
 */
@Data
public class AlarmRes {

    private Long id;

    @ApiModelProperty(value = "报警内容")
    private String content;

    @ApiModelProperty(value = "报警级别")
    private Integer alarmLevel;

    @ApiModelProperty(value = "报警产生时间")
    private Date timeAdd;

    @ApiModelProperty(value = "报警类型(0->阅读器断开;1->资产进出报警;2->资产外出超时报警)")
    private Integer alarmType;

    @ApiModelProperty(value = "源记录ID")
    private Long sourceID;

    @ApiModelProperty(value = "是否邮件报警(0->否;1->是)")
    private Integer alarmEmail;

    @ApiModelProperty(value = "发送是否成功(0->否;1->是)")
    private Integer isSend;

    @ApiModelProperty(value = "接收邮箱")
    private String email;

    @ApiModelProperty(value = "资产编号")
    private String assetNo;

    @ApiModelProperty(value = "资产名称")
    private String assetName;

}
