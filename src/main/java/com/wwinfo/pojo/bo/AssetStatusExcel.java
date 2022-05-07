package com.wwinfo.pojo.bo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-02-23 20:04
 */
@Data
public class AssetStatusExcel extends BaseRowModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ExcelProperty(value = "所属部门名称", index = 0)
    private String orgName;

    @ExcelProperty(value = "资产名称", index = 1)
    private String name;

    @ExcelProperty(value = "资产编号", index = 2)
    private String assetNo;

    @ExcelProperty(value = "当前状态", index = 3)
    private String curStatus;

    @ExcelProperty(value = "使用部门", index = 4)
    private String useOrg;

    @ExcelProperty(value = "品牌", index = 5)
    private String brand;

    @ExcelProperty(value = "型号", index = 6)
    private String model;

    @ExcelProperty(value = "序列号", index = 7)
    private String serialNo;

    @ExcelProperty(value = "创建时间", index = 8)
    private String timeAdd;

}
