package com.wwinfo.service;

import com.wwinfo.model.Entrypos;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * RFID进出口设置表 服务类
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
public interface EntryposService extends IService<Entrypos> {

    List<HashMap<String,Object>> getByParam(Map<String, Object> map);

}
