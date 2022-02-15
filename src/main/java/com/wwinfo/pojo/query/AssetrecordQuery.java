package com.wwinfo.pojo.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-02-15 14:03
 */
@Data
public class AssetrecordQuery {

    @ApiModelProperty(value = "进出方向(0->进；1->出)")
    private Integer directory;

    @ApiModelProperty(value = "原状态(0->内部；1->外部)")
    private Integer preStatus;

    @ApiModelProperty(value = "原状态是否正确(0->正常；1->原状态不匹配)")
    private Integer statusMatched;

    @ApiModelProperty(value = "是否报警(0->不报警;1->报警)")
    private Integer isAlarm;

    @ApiModelProperty(value = "报警类型(1->异常外出;2->进出状态不匹配;4->黑名单;5->销毁资产)")
    private Integer alarmType;

}
