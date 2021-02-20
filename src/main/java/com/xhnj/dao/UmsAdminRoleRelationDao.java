package com.xhnj.dao;

import com.xhnj.model.UmsAdminRoleRelation;
import com.xhnj.model.UmsRole;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/*
 @Description 后台用户与角色管理自定义Dao
 *@author kang.li
 *@date 2020/7/29 16:40   
 */
public interface UmsAdminRoleRelationDao {

    /**
     * 获取系统用户角色列表
     * @param userId
     * @return
     */
    List<UmsRole> getRoleList(@Param("userId") Long userId);

    int insertList(@Param("list") List<UmsAdminRoleRelation> list);
}
