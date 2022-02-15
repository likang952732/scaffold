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

    @ApiModelProperty(value = "操作描述信息")
    private String descript;

    @ApiModelProperty(value = "开始日期(yyyy-MM-dd)")
    private String startDate;

    @ApiModelProperty(value = "结束日期(yyyy-MM-dd)")
    private String endDate;

}
