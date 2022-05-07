package com.wwinfo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 人员库
 * </p>
 *
 * @author lk
 * @since 2022-04-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PersonnelLib implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 所属部门ID
     */
    @TableField("orgID")
    private Long orgID;

    /**
     * 所属部门名称
     */
    private String orgName;

    /**
     * 姓名
     */
    private String fullName;

    /**
     * 工号
     */
    private String jobNo;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 状态：0->显示；1->不显示；
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    private Date updateTime;

}
