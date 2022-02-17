package com.wwinfo.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 资产允许或不允许位置
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Assetpos implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 资产ID
     */
    @TableId("assetID")
    private Long assetID;

    /**
     * RFID进出口位置ID
     */
    @TableField("posID")
    private Long posID;


}
