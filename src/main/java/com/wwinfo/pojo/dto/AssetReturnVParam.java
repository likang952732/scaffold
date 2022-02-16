package com.wwinfo.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-02-15 20:28
 */
@Data
public class AssetReturnVParam {

    @ApiModelProperty(value = "资产id", required = true)
    @NotNull(message = "资产id不能为空")
    private Long assetID;

    @ApiModelProperty(value = "归还时间", required = true)
    @NotNull(message = "归还时间不能为空")
    private Date returnTime;

}
