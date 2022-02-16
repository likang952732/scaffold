package com.wwinfo.pojo.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-02-16 21:23
 */
@Data
public class EntryposQuery {

    @ApiModelProperty(value = "首次读到的阅读器名称")
    private String startReaderName;

    @ApiModelProperty(value = "最后读到的阅读器名称")
    private String endReaderName;

    @ApiModelProperty(value = "进出方向(0->进；1->出)")
    private Integer directory;

}
