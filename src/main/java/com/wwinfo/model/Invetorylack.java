package com.wwinfo.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 资产盘点缺少表
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Invetorylack implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("taskID")
    private Long taskID;

    /**
     * 资产ID
     */
    @TableField("assetID")
    private Long assetID;

    /**
     * 应该在的库房ID
     */
    @TableField("shouldRoomID")
    private Long shouldRoomID;

    /**
     * 盘点结果确认
     */
    @TableField("resultCheck")
    private Integer resultCheck;


}
