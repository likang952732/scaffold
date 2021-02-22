package com.xhnj.dao;

import com.xhnj.model.ShareStrategy;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2021/2/22 17:07   
 */
public interface ShareStrategyDao {
    int insertList(@Param("list") List<ShareStrategy> list);
}
