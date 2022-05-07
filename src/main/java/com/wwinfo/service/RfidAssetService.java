package com.wwinfo.service;

import com.wwinfo.model.RfidAsset;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-04-21 23:25
 */
public interface RfidAssetService {

    int addBatch(List<RfidAsset> list, int count);

}
