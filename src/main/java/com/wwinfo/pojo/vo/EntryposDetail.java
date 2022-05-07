package com.wwinfo.pojo.vo;

import com.wwinfo.model.Rfidreader;
import com.wwinfo.pojo.res.EntryposRes;
import lombok.Data;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-04-16 18:54
 */
@Data
public class EntryposDetail extends EntryposRes {

    private List<Rfidreader> startReadList;

    private List<Rfidreader> endReadList;

}
