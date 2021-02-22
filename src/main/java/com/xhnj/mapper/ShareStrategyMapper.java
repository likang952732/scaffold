package com.xhnj.mapper;

import com.xhnj.model.ShareStrategy;
import com.xhnj.model.ShareStrategyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShareStrategyMapper {
    long countByExample(ShareStrategyExample example);

    int deleteByExample(ShareStrategyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ShareStrategy record);

    int insertSelective(ShareStrategy record);

    List<ShareStrategy> selectByExampleWithBLOBs(ShareStrategyExample example);

    List<ShareStrategy> selectByExample(ShareStrategyExample example);

    ShareStrategy selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ShareStrategy record, @Param("example") ShareStrategyExample example);

    int updateByExampleWithBLOBs(@Param("record") ShareStrategy record, @Param("example") ShareStrategyExample example);

    int updateByExample(@Param("record") ShareStrategy record, @Param("example") ShareStrategyExample example);

    int updateByPrimaryKeySelective(ShareStrategy record);

    int updateByPrimaryKeyWithBLOBs(ShareStrategy record);

    int updateByPrimaryKey(ShareStrategy record);
}