package com.wwinfo.service;

import com.wwinfo.model.RfidMapping;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-02-28 14:41
 */
public interface RfidMappingService {

    RfidMapping getByMap(Map<String, Object> map);

}
