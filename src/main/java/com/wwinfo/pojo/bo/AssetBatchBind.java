package com.wwinfo.pojo.bo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-04-21 23:16
 */
@Data
public class AssetBatchBind extends BaseRowModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "资产编号不能为空")
    @ExcelProperty(value = "资产编号", index = 0)
    private String assetNo;

    @NotBlank(message = "rfid打印编号不能为空")
    @ExcelProperty(value = "rfid打印编号", index = 1)
    private String rfidPrintNo;

}
