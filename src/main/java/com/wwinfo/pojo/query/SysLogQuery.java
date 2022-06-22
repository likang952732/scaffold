package com.wwinfo.pojo.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-02-15 12:22
 */
@Data
public class SysLogQuery {

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "操作动作")
    private String operate;

    @ApiModelProperty(value = "操作对象类型")
    private String objectType;

    @ApiModelProperty(value = "操作对象名称")
    private String objectName;

    @ApiModelProperty(value = "开始日期(yyyy-MM-dd)")
    private String startDate;

    @ApiModelProperty(value = "结束日期(yyyy-MM-dd)")
    private String endDate;

    @ApiModelProperty(value = "每页显示条数(默认为10条)")
    private Integer pageSize = 10;

    @ApiModelProperty(value = "页码")
    private Integer pageNum = 1;

}
