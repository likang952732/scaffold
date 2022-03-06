package com.wwinfo.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-02-28 11:10
 */
@Data
public class BindRFIDVO {

    @ApiModelProperty(value = "资产ID", required = true)
    @NotNull(message = "资产ID不能为空")
    private Long assetID;

    @ApiModelProperty(value = "RFID打印编号", required = true)
    @NotNull(message = "rfidPrintNo不能为空")
    private String rfidPrintNo;

}
