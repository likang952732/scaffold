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

    @ApiModelProperty(value = "参数名称", required = true)
    @NotBlank(message = "name不能为空")
    private String name;

    /**
     * 参数键名
     */
    @ApiModelProperty(value = "参数键名", required = true)
    @NotBlank(message = "fieldName不能为空")
    private String fieldName;

    /**
     * 参数键值
     */
    @ApiModelProperty(value = "参数键值", required = true)
    @NotBlank(message = "value不能为空")
    private String value;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

}
