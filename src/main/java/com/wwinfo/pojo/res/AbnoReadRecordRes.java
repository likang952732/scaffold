package com.wwinfo.pojo.res;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-05-03 18:34
 */
@Data
public class AbnoReadRecordRes {

    private Long id;

    private Long assetID;

    private String assetName;

    private String assetNo;

    /**
     * 阅读器名称
     */
    private String readerName;

    /**
     * 描述
     */
    private String abDescription;

    private Date createTime;

}
