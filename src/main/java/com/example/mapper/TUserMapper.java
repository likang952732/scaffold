package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.model.TUser;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 * 用户Mapper 接口
 * </p>
 *
 * @author lk
 * @since 2021-02-24
 */
public interface TUserMapper extends BaseMapper<TUser> {
    int updateByPhone(@Param("map") Map<String, Object> map);

    TUser getUserInfo(@Param("username") String username);
}
