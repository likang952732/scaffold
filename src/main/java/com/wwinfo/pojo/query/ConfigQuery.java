package com.wwinfo.pojo.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-02-17 18:02
 */
@Data
public class ConfigQuery {

    @ApiModelProperty(value = "参数名称")
    private String name;

    @ApiModelProperty(value = "每页显示条数(默认为10条)")
    private Integer pageSize = 10;

    @ApiModelProperty(value = "页码")
    private Integer pageNum = 1;

}
