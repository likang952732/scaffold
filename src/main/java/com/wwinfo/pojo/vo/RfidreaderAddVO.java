package com.wwinfo.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-02-16 11:37
 */
@Data
public class RfidreaderAddVO {

    @ApiModelProperty(value = "阅读器名称", required = true)
    @NotBlank(message = "readerName不能为空")
    private String readerName;

    @ApiModelProperty(value = "阅读器IP地址", required = true)
    @NotBlank(message = "readerIP不能为空")
    private String readerIP;

    @ApiModelProperty(value = "所在位置")
    private String address;

    @ApiModelProperty(value = "品牌")
    private String brand;

    @ApiModelProperty(value = "设备型号")
    private String model;

    @ApiModelProperty(value = "接口分类(0->有源（M5); 1->有源（自有); 2->无源（1天线); 3->无源（2天线); 4->无源（3天线) 5->无源（4天线）)", required = true)
    @NotNull(message = "interfaceType不能为空")
    private Integer interfaceType;

    @ApiModelProperty(value = "是否连接报警器(0->否;1->是)", required = true)
    @NotNull(message = "hasAlarm不能为空")
    private Integer hasAlarm;

    @ApiModelProperty(value = "安装日期", required = true)
    @NotNull(message = "setupDate不能为空")
    private Date setupDate;

    @ApiModelProperty(value = "断线是否报警(0->否;1->是)", required = true)
    @NotNull(message = "isAlarm不能为空")
    private Integer isAlarm;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

}
