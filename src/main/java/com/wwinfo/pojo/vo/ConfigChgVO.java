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
 * DateTime: 2022-02-17 14:17
 */
@Data
public class ConfigChgVO {

    @ApiModelProperty(value = "id", required = true)
    @NotNull(message = "id不能为空")
    private Long id;

    @ApiModelProperty(value = "参数名称(最大长度32)", required = true)
    @NotBlank(message = "name不能为空")
    private String name;

    private String fieldName;

    @ApiModelProperty(value = "参数键值(最大长度64)", required = true)
    @NotBlank(message = "value不能为空")
    private String value;

    @ApiModelProperty(value = "备注(最大长度256)")
    private String remark;

}
