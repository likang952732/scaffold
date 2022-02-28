package com.wwinfo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-02-28 14:37
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RfidMapping implements Serializable {

    private static final long serialVersionUID = 1L;

    private String rfidPrintNo;

    private String rfidRealNo;

    private Integer status;

}
