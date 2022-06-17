package com.wwinfo.pojo.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-02-17 18:06
 */
@Data
public class InvetorylackQuery {

    @ApiModelProperty(value = "盘点结果确认(0->未确认;1->确认)")
    private Integer resultCheck;

    private Long orgID;

    @ApiModelProperty(value = "每页显示条数(默认为10条)")
    private Integer pageSize = 10;

    @ApiModelProperty(value = "页码")
    private Integer pageNum = 1;

}