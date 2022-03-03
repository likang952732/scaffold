package com.wwinfo.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
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

 /*   @ApiModelProperty(value = "首次读到的阅读器名称")
    private String startReaderName;

    @ApiModelProperty(value = "最后读到的阅读器名称")
    private String endReaderName;*/

    @ApiModelProperty(value = "进出方向(0->进；1->出)")
    private Integer directory;

    @ApiModelProperty(value = "每页显示条数(默认为10条)")
    private Integer pageSize = 10;

    @ApiModelProperty(value = "页码")
    private Integer pageNum = 1;

}
