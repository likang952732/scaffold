package com.wwinfo.pojo.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-02-15 14:12
 */
@Data
public class RfidrecordQuery {

    @ApiModelProperty(value = "进出计算结果(0->计算中;1->确认为进；2->确认为出；3->忽略)")
    private Integer status;

    @ApiModelProperty(value = "是否外部RFID(0->不是外部的RFID;1->外部的RFID)")
    private Integer isOther;

    @ApiModelProperty(value = "每页显示条数(默认为10条)")
    private Integer pageSize = 10;

    @ApiModelProperty(value = "页码")
    private Integer pageNum = 1;

}
