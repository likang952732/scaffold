package com.wwinfo.pojo.res;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-04-15 17:10
 */
@Data
public class PersonnelLibRes {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "所属部门ID")
    private Long orgID;

    @ApiModelProperty(value = "所属部门名称")
    private String orgName;

    @ApiModelProperty(value = "姓名")
    private String fullName;

    @ApiModelProperty(value = "工号")
    private String jobNo;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "状态：0->显示；1->不显示")
    private Integer status;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

}
