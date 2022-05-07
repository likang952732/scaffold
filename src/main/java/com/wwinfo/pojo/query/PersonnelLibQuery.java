package com.wwinfo.pojo.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-04-15 17:07
 */
@Data
public class PersonnelLibQuery {

    @ApiModelProperty(value = "部门id")
    private Long orgID;

    private Long canorgID;

    @ApiModelProperty(value = "姓名")
    private String fullName;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "每页显示条数(默认为10条)")
    private Integer pageSize = 10;

    @ApiModelProperty(value = "页码")
    private Integer pageNum = 1;

}
