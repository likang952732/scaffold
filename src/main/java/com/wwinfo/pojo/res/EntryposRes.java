package com.wwinfo.pojo.res;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-02-16 21:15
 */
@Data
public class EntryposRes {

    private Long ID;

    @ApiModelProperty(value = "位置名称")
    private String posName;

    @ApiModelProperty(value = "首次读到的阅读器ID")
    private Long startReaderID;

    @ApiModelProperty(value = "首次读到的阅读器名称")
    private String startReaderName;

    @ApiModelProperty(value = "最后读到的阅读器ID")
    private Long endReaderID;

    @ApiModelProperty(value = "最后读到的阅读器名称")
    private String endReaderName;

    @ApiModelProperty(value = "关联库房ID")
    private Long roomID;

    @ApiModelProperty(value = "关联库房名称")
    private String roomName;

    @ApiModelProperty(value = "进出方向(0->进；1->出)")
    private Integer directory;

    @ApiModelProperty(value = "是否报警(0->不报警；1->报警)")
    private Integer isAlarm;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "设置时间")
    private Date timeAdd;

    @ApiModelProperty(value = "更新时间")

    private Date timeModify;

}
