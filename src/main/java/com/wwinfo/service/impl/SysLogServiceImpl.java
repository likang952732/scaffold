package com.wwinfo.service.impl;

import com.wwinfo.mapper.TLogMapper;
import com.wwinfo.model.TLog;
import com.wwinfo.service.SysLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysLogServiceImpl implements SysLogService {


    @Resource
    private TLogMapper logMapper;

    @Override
    public int insertAdmin(TLog tLog) {
        return logMapper.insert(tLog);
    }
}
