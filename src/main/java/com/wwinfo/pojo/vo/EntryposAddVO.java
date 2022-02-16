package com.wwinfo.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-02-16 21:25
 */
@Data
public class EntryposAddVO {

    @ApiModelProperty(value = "位置名称", required = true)
    @NotBlank(message = "posName不能为空")
    private String posName;

    @ApiModelProperty(value = "首次读到的阅读器ID", required = true)
    @NotNull(message = "startReaderID不能为空")
    private Long startReaderID;


    @ApiModelProperty(value = "最后读到的阅读器ID")
    private Long endReaderID;

    @ApiModelProperty(value = "关联库房ID")
    private Long roomID;

    @ApiModelProperty(value = "进出方向(0->进；1->出)", required = true)
    @NotNull(message = "directory不能为空")
    private Integer directory;

    @ApiModelProperty(value = "是否报警(0->不报警；1->报警)", required = true)
    @NotNull(message = "isAlarm不能为空")
    private Integer isAlarm;

    @ApiModelProperty(value = "备注")
    private String remark;

}
