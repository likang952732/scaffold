package com.wwinfo.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-02-16 14:19
 */
@Data
public class RoomAddVO {

    @ApiModelProperty(value = "库房名称")
    @NotBlank(message = "roomName不能为空")
    private String roomName;

    @ApiModelProperty(value = "位置描述")
    private String address;

    @ApiModelProperty(value = "库房说明")
    private String desc;

    @ApiModelProperty(value = "库房等级")
    private String level;

    @ApiModelProperty(value = "责任人")
    private String responsible;

    @ApiModelProperty(value = "联系电话")
    private String phone;

    @ApiModelProperty(value = "备注")
    private String remark;

}
