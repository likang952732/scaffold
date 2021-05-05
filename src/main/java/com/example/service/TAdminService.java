package com.example.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.model.TAdmin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.model.TOrder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author lk
 * @since 2021-02-24
 */
public interface TAdminService extends IService<TAdmin> {
    String login(String username, String password);

    TAdmin register(TAdmin admin);

    TAdmin getAdminByUsername(String username);

    Page getUserPage(String keyword, Integer pageSize, Integer pageNum);

    UserDetails loadUserByUsername(String username);

    int updateAdmin(TAdmin admin);

    int deleteAdmin(Long id);

    int releaseOrder(TOrder order);

}
