package com.wwinfo.pojo.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-02-15 13:48
 */
@Data
public class AlarmQuery {

    @ApiModelProperty(value = "级别")
    private Integer level;

    @ApiModelProperty(value = "报警类型(0->阅读器断开;1->资产进出报警;2->资产外出超时报警)")
    private Integer alarmType;

    @ApiModelProperty(value = "是否邮件报警(0->否;1->是)")
    private Integer alarmEmail;

    @ApiModelProperty(value = "发送是否成功(0->否;1->是)")
    private Integer isSend;

}
