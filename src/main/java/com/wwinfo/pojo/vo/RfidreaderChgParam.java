package com.wwinfo.pojo.vo;

import com.wwinfo.annotation.IpCheck;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-02-16 11:40
 */
@Data
public class RfidreaderChgParam {

    @ApiModelProperty(value = "id")
    @NotNull(message = "id不能为空")
    private Long id;

    @ApiModelProperty(value = "阅读器名称(最大长度60)", required = true)
    @NotBlank(message = "readerName不能为空")
    private String readerName;

    @ApiModelProperty(value = "阅读器IP地址(最大长度30)", required = true)
    @NotBlank(message = "readerIP不能为空")
    @IpCheck
    private String readerIP;

    @ApiModelProperty(value = "所在位置(最大长度300)")
    private String address;

    @ApiModelProperty(value = "品牌(最大长度30)")
    private String brand;


    @ApiModelProperty(value = "设备型号(最大长度30)")
    private String model;

    @ApiModelProperty(value = "接口分类(0->有源（M5); 1->有源（自有); 2->无源（1天线); 3->无源（2天线); 4->无源（3天线) 5->无源（4天线）)", required = true)
    @NotNull(message = "interfaceType不能为空")
    private Integer interfaceType;

    @ApiModelProperty(value = "是否连接报警器(0->否;1->是)", required = true)
    @NotNull(message = "hasAlarm不能为空")
    private Integer hasAlarm;

    @ApiModelProperty(value = "安装日期(yyyy-MM-dd)", required = true)
    @NotNull(message = "setupDate不能为空")
    private String setupDate;

    @ApiModelProperty(value = "断线是否报警(0->否;1->是)", required = true)
    @NotNull(message = "isAlarm不能为空")
    private Integer isAlarm;

    @ApiModelProperty(value = "备注(最大长度90)")
    private String remark;

}
