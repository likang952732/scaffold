package com.wwinfo.pojo.query;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-05-03 18:37
 */
@Data
public class AbnoReadRecordQuery {

    @JsonIgnore
    private Long canorgID;

    private Long assetID;

    private String assetNo;

    private String startDate;

    private String endDate;

    @ApiModelProperty(value = "每页显示条数(默认为10条)")
    private Integer pageSize = 10;

    @ApiModelProperty(value = "页码")
    private Integer pageNum = 1;

}
