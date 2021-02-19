package com.xhnj.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2021/2/19 15:42   
 */
public interface HomeAdvertiseDao {
    /**
     * 批量删除
     * @param ids
     */
    public void deleteByIds(@Param("ids")List<Long> ids);
}
