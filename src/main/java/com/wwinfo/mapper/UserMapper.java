package com.wwinfo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwinfo.model.User;
import com.wwinfo.pojo.query.UserQuery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 部门管理员表表 Mapper 接口
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
public interface UserMapper extends BaseMapper<User> {

    User getByUserName(@Param("userName") String userName);

}
