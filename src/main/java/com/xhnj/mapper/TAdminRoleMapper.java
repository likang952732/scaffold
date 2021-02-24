package com.xhnj.mapper;

import com.xhnj.model.TAdminRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用户角色表 Mapper 接口
 * </p>
 *
 * @author lk
 * @since 2021-02-24
 */
public interface TAdminRoleMapper extends BaseMapper<TAdminRole> {
    int addBatch(List<TAdminRole> roleMenuList);

    int deleteByUserId(Long userId);

}
