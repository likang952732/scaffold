package com.wwinfo.pojo.bo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-02-24 11:10
 */
@Data
public class AssetImport extends BaseRowModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "资产编号不能为空")
    @ExcelProperty(value = "资产编号", index = 0)
    private String assetNo;

    @ExcelProperty(value = "所属部门id", index = 1)
    private String orgID;

    @ExcelProperty(value = "资产名称", index = 2)
    private String name;

    @ExcelProperty(value = "说明", index = 3)
    private String description;

    @ExcelProperty(value = "责任人", index = 4)
    private String staff;

    @ExcelProperty(value = "设备大类", index = 5)
    private String largeClass;

    @ExcelProperty(value = "设备小类", index = 6)
    private String littleClass;

    @ExcelProperty(value = "型号", index = 7)
    private String model;

    @ExcelProperty(value = "计量有效期", index = 8)
    private String period;

    @ExcelProperty(value = "价格(元)", index = 9)
    private BigDecimal price;

    @ExcelProperty(value = "设备密级", index = 10)
    private String secLevel;

    @ExcelProperty(value = "序列号", index = 11)
    private String serialNo;

}
