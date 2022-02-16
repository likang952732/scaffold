package com.wwinfo.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-02-16 14:24
 */
@Data
public class RoomChgVO {

    @ApiModelProperty(value = "库房id")
    @NotNull(message = "id不能为空")
    private Long id;

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
