package com.wwinfo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wwinfo.model.TUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-03-03 10:54
 */
public interface TUserRoleMapper extends BaseMapper<TUserRole> {

    int addBatch(@Param("userRoleList") List<TUserRole> userRoleList);

    int deleteByUserId(Long userId);

}
