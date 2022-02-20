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

    @ApiModelProperty(value = "库房名称(最大长度60)")
    @NotBlank(message = "roomName不能为空")
    private String roomName;

    @ApiModelProperty(value = "位置描述(最大长度90)")
    private String address;

    @ApiModelProperty(value = "库房说明(最大长度90)")
    private String description;

    @ApiModelProperty(value = "库房等级(最大长度30)")
    private String roomLevel;

    @ApiModelProperty(value = "责任人(最大长度30)")
    private String responsible;

    @ApiModelProperty(value = "联系电话(最大长度30)")
    private String phone;

    @ApiModelProperty(value = "备注(最大长度90)")
    private String remark;

}
