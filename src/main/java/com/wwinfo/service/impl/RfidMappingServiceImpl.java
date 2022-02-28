package com.wwinfo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwinfo.common.ExcludeEmptyQueryWrapper;
import com.wwinfo.mapper.RfidMappingMapper;
import com.wwinfo.model.RfidMapping;
import com.wwinfo.model.Rfidrecord;
import com.wwinfo.service.RfidMappingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-02-28 14:42
 */
@Service
public class RfidMappingServiceImpl extends ServiceImpl<RfidMappingMapper, RfidMapping> implements RfidMappingService {

    @Resource
    private RfidMappingMapper rfidMappingMapper;

    @Override
    public RfidMapping getByMap(Map<String, Object> map) {
        QueryWrapper<RfidMapping> wrapper = new ExcludeEmptyQueryWrapper<>();
        wrapper.eq("rfid_print_no", map.get("rfidPrintNo"));
        wrapper.eq("rfid_real_no", map.get("rfidRealNo"));
        return rfidMappingMapper.selectOne(wrapper);
    }
}
