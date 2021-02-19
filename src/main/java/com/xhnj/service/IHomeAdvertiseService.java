package com.xhnj.service;

import com.xhnj.model.HomeAdvertise;

import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2021/2/19 14:22   
 */
public interface IHomeAdvertiseService {
    List<HomeAdvertise> list(Integer status, Integer pageSize, Integer pageNum);

    int create(HomeAdvertise homeAdvertise);

    int update(HomeAdvertise homeAdvertise);

    int delete(List<Long> ids);
}
