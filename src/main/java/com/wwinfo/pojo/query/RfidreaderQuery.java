package com.wwinfo.pojo.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-02-16 11:23
 */
@Data
public class RfidreaderQuery {

    @ApiModelProperty(value = "阅读器名称")
    private String readerName;

    @ApiModelProperty(value = "接口分类")
    private Integer interfaceType;

}
