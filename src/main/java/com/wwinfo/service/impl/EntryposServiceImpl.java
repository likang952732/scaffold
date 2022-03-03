package com.wwinfo.service.impl;

import com.wwinfo.model.Entrypos;
import com.wwinfo.mapper.EntryposMapper;
import com.wwinfo.service.EntryposService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * RFID进出口设置表 服务实现类
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Service
public class EntryposServiceImpl extends ServiceImpl<EntryposMapper, Entrypos> implements EntryposService {

    @Resource
    private EntryposMapper entryposMapper;

    @Override
    public List<HashMap<String, Object>> getByParam(Map<String, Object> map) {
        return entryposMapper.getByParam(map);
    }
}
