package com.xhnj.service;


import com.xhnj.dto.UmsAdminParam;
import com.xhnj.model.UmsAdmin;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/7/29 14:56   
 */
public interface UmsAdminService {
    int updateAdmin(Long id, UmsAdmin admin);

    int updateRole(Long userId, List<Long> roleIds);

    int deleteAdmin(Long id);

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username, String password);

    /**
     * 注册功能
     */
    UmsAdmin register(UmsAdminParam umsAdminParam);

    /**
     * 获取用户信息
     */
    UserDetails loadUserByUsername(String username);

    UmsAdmin getAdminByUsername(String userName);

    List<UmsAdmin> getUserPage(String keyword, Integer pageSize, Integer pageNum);
}
