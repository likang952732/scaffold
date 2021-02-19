package com.xhnj.mapper;

import com.xhnj.model.HomeAdvertise;
import com.xhnj.model.HomeAdvertiseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HomeAdvertiseMapper {
    long countByExample(HomeAdvertiseExample example);

    int deleteByExample(HomeAdvertiseExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HomeAdvertise record);

    int insertSelective(HomeAdvertise record);

    List<HomeAdvertise> selectByExample(HomeAdvertiseExample example);

    HomeAdvertise selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HomeAdvertise record, @Param("example") HomeAdvertiseExample example);

    int updateByExample(@Param("record") HomeAdvertise record, @Param("example") HomeAdvertiseExample example);

    int updateByPrimaryKeySelective(HomeAdvertise record);

    int updateByPrimaryKey(HomeAdvertise record);
}