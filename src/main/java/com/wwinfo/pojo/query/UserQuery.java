package com.wwinfo.pojo.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-02-14 22:18
 */
@Data
public class UserQuery {

    @ApiModelProperty(value = "用户名称")
    private String username;

}
