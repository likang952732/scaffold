package com.wwinfo.pojo.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-02-15 14:33
 */
@Data
public class InvetoryassetQuery {

    @ApiModelProperty(value = "盘点结果类型(0->吻合;1->此资产应该在另一个库房;2->此资产应该在外面)")
    private Integer checkResult;

    @ApiModelProperty(value = "盘点结果确认(0->未确认;1->确认)")
    private Integer resultCheck;

    @ApiModelProperty(value = "每页显示条数(默认为10条)")
    private Integer pageSize = 10;

    @ApiModelProperty(value = "页码")
    private Integer pageNum = 1;

}
