package com.wwinfo.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-03-03 15:34
 */
@Data
public class MenuChgVO {

    @ApiModelProperty(value = "菜单id", required = true)
    @NotNull(message = "id不能为空")
    private Long id;

    @ApiModelProperty(value = "菜单名称", required = true)
    @NotBlank(message = "菜单名称不能为空")
    private String name;

    @ApiModelProperty(value = "父级id")
    private Long parentId;

    @ApiModelProperty(value = "排序", required = true)
    @NotNull(message = "sort不能为空")
    private Integer sort;

    @ApiModelProperty(value = "状态：0->显示；1->不显示", required = true)
    @NotNull(message = "状态不能为空")
    private Integer status;

    @ApiModelProperty(value = "菜单类型：0->部门；1->系统", required = true)
    @NotNull(message = "菜单类型不能为空")
    private Integer type;

    @ApiModelProperty(value = "前端名称")
    private String frontName;

    @ApiModelProperty(value = "前端图标")
    private String frontImg;

}
