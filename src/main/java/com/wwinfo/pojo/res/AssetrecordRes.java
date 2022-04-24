package com.wwinfo.pojo.res;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description: 资产进出记录
 *
 * @Author: lk
 * DateTime: 2022-02-16 10:58
 */
@Data
public class AssetrecordRes {

    @ApiModelProperty(value = "进出记录ID")
    private Long ID;

    @ApiModelProperty(value = "资产ID")
    private Long assetID;

    @ApiModelProperty(value = "资产名称")
    private String assetName;

    @ApiModelProperty(value = "位置ID")
    private Long posID;

    @ApiModelProperty(value = "位置名称")
    private String posName;

    @ApiModelProperty(value = "进出方向(0->进；1->出)")
    private Integer directory;

    @ApiModelProperty(value = "进出时间")
    private Date timeAdd;

    @ApiModelProperty(value = "原状态(0->内部；1->外部)")
    private Integer preStatus;

    @ApiModelProperty(value = "原状态时间")
    private Date timePreStatus;

    @ApiModelProperty(value = "原状态保持时间(单位：小时)")
    private Integer statusTimes;

    @ApiModelProperty(value = "原状态是否正确(0->正常；1->原状态不匹配)")
    @TableField("statusMatched")
    private Integer statusMatched;

    @ApiModelProperty(value = "是否报警(0->不报警;1->报警)")
    private Integer isAlarm;

    @ApiModelProperty(value = "报警类型(1->异常外出;2->进出状态不匹配;4->黑名单;5->销毁资产)")
    private Integer alarmType;

    @ApiModelProperty(value = "部门名称")
    private String orgName;

}
