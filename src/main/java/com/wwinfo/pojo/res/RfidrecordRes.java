package com.wwinfo.pojo.res;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-04-16 16:12
 */
@Data
public class RfidrecordRes {

    @ApiModelProperty(value = "id")
    private Long ID;

    @ApiModelProperty(value = "RFID编号")
    private String rfidNo;

    @ApiModelProperty(value = "阅读器ID")
    private Long readerID;

    @ApiModelProperty(value = "读到时间")
    private Date timeAdd;

    @ApiModelProperty(value = "进出计算结果(0->计算中;1->确认为进；2->确认为出；3->忽略)")
    private Integer status;

    @ApiModelProperty(value = "是否绑定了资产(1->绑定)")
    private Integer isAsset;

    @ApiModelProperty(value = "资产ID")
    private Long assetID;

    @ApiModelProperty(value = "资产名称")
    private String assetName;

    @ApiModelProperty(value = "是否外部RFID(0->不是外部的RFID;1->外部的RFID)")
    private Integer isOther;

    @ApiModelProperty(value = "所属部门名称")
    private String orgName;

}
