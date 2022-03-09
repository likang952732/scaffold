package com.wwinfo.mapper;

import com.wwinfo.model.Rfidreader;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * RFID阅读器表 Mapper 接口
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
public interface RfidreaderMapper extends BaseMapper<Rfidreader> {

    List<HashMap<String,Object>> getAllReader();

}
