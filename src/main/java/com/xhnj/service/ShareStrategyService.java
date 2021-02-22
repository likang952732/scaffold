package com.xhnj.service;

import com.xhnj.model.ShareStrategy;

import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2021/2/22 13:55   
 */
public interface ShareStrategyService {
    List<ShareStrategy> list(Integer status, Integer pageSize, Integer pageNum);

    int create(ShareStrategy shareStrategy);

    int updateStatus(List<Long> ids, Integer status);

}
