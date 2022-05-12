package com.wwinfo.pojo.bo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
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
@ContentRowHeight(15) //内容行高
@HeadRowHeight(23)//表头行高
@Data
public class AssetStatusExcel extends BaseRowModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ColumnWidth(20)
    @ExcelProperty(value = "所属部门名称", index = 0)
    private String orgName;

    @ColumnWidth(25)
    @ExcelProperty(value = "资产名称", index = 1)
    private String name;

    @ColumnWidth(25)
    @ExcelProperty(value = "资产编号", index = 2)
    private String assetNo;

    @ColumnWidth(20)
    @ExcelProperty(value = "当前状态", index = 3)
    private String curStatus;

    @ColumnWidth(20)
    @ExcelProperty(value = "使用部门", index = 4)
    private String useOrg;

    @ColumnWidth(20)
    @ExcelProperty(value = "品牌", index = 5)
    private String brand;

    @ColumnWidth(20)
    @ExcelProperty(value = "型号", index = 6)
    private String model;

    @ColumnWidth(20)
    @ExcelProperty(value = "序列号", index = 7)
    private String serialNo;

    @ColumnWidth(25)
    @ExcelProperty(value = "创建时间", index = 8)
    private String timeAdd;

}
