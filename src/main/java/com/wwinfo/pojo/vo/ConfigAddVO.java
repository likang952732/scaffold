package com.wwinfo.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-02-17 13:57
 */
@Data
public class ConfigAddVO {

    @ApiModelProperty(value = "参数名称(最大长度32)", required = true)
    @NotBlank(message = "name不能为空")
    private String name;

    @ApiModelProperty(value = "参数键名(最大长度32)", required = true)
    @NotBlank(message = "fieldName不能为空")
    private String fieldName;

    @ApiModelProperty(value = "参数键值(最大长度64)", required = true)
    @NotBlank(message = "value不能为空")
    private String value;

    @ApiModelProperty(value = "备注(最大长度256)")
    private String remark;

}
