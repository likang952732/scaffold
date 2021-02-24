package com.xhnj.service;

import com.xhnj.model.TUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author lk
 * @since 2021-02-24
 */
public interface ITUserService extends IService<TUser> {
    String login(String username, String password);

    TUser register(TUser user);

    TUser getUserByUsername(String username);

    public UserDetails loadUserByUsername(String username);

}
