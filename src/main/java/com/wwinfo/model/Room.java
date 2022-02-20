package com.wwinfo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 库房
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long ID;

    /**
     * 库房名称
     */
    @TableField("roomName")
    @ApiModelProperty(value = "库房名称")
    private String roomName;

    /**
     * 位置描述
     */
    @ApiModelProperty(value = "位置描述")
    private String address;

    /**
     * 库房说明
     */
    @ApiModelProperty(value = "库房说明")
    private String description;

    /**
     * 库房等级
     */
    @TableField("roomLevel")
    @ApiModelProperty(value = "库房等级")
    private String roomLevel;

    /**
     * 责任人
     */
    @ApiModelProperty(value = "责任人")
    private String responsible;

    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话")
    private String phone;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    @TableField("timeAdd")
    @ApiModelProperty(value = "添加时间")
    private Date timeAdd;

}
