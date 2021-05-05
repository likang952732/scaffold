package com.example.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.bo.AdminUserDetails;
import com.example.common.exception.BusinessException;
import com.example.mapper.TOrderMapper;
import com.example.model.TAdmin;
import com.example.mapper.TAdminMapper;
import com.example.model.TOrder;
import com.example.service.TAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.util.JwtTokenUtil;
import com.example.util.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author lk
 * @since 2021-02-24
 */
@Service
@Slf4j
public class TAdminServiceImpl extends ServiceImpl<TAdminMapper, TAdmin> implements TAdminService {
    @Resource
    private TAdminMapper adminMapper;
    @Resource
    private TOrderMapper orderMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Override
    public String login(String username, String password) {
        String token = null;
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if(!passwordEncoder.matches(password,userDetails.getPassword())){
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
            TAdmin user = UserUtil.getCurrentAdminUser();
            user.setLoginTime(DateUtil.date());
            adminMapper.updateById(user);
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public TAdmin register(TAdmin admin) {
        String encodePassword = passwordEncoder.encode(admin.getPassword());
        admin.setPassword(encodePassword);
        adminMapper.insert(admin);
        return null;
    }

    @Override
    public TAdmin getAdminByUsername(String username) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("username", username);
        return adminMapper.selectOne(wrapper);
    }

    @Override
    public Page getUserPage(String keyword, Integer pageSize, Integer pageNum) {
        Page<TAdmin> page = new Page<>(pageNum, pageSize);
        QueryWrapper wrapper = new QueryWrapper();
        if(StrUtil.isNotBlank(keyword)) {
            wrapper.like("name", keyword);
        }
        return adminMapper.selectPage(page, wrapper);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        //获取用户信息
        TAdmin admin = getAdminByUsername(username);
        if (admin != null) {
            // List<UmsResource> resourceList = getResourceList(admin.getId());
            return new AdminUserDetails(admin);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    @Override
    public int updateAdmin(TAdmin admin) {
        return adminMapper.updateById(admin);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteAdmin(Long id) {
        if(id == null)
            throw new BusinessException("用户id不能为空");
        return adminMapper.deleteById(id);
    }

    @Override
    public int releaseOrder(TOrder order) {
        TAdmin admin = UserUtil.getCurrentAdminUser();
        order.setAdminId(admin.getId());
        return orderMapper.insert(order);
    }
}
