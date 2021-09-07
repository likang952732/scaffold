package com.example.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.dto.WxLoginParam;
import com.example.mapper.TUserMapper;
import com.example.mapper.ThildUserMapper;
import com.example.model.TThildUserAuth;
import com.example.model.TUser;
import com.example.service.TUserService;
import com.example.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/*
 @Description
 *@author kang.li
 *@date 2021/9/7 15:28   
 */
@Service
public class TUserServiceImpl implements TUserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private ThildUserMapper thildUserMapper;
    @Resource
    private TUserMapper userMapper;


    @Value("${wechat.initpwd}")
    private String initpwd;
    @Value("${wechat.initnickname}")
    private String initnickname;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String wxLogin(WxLoginParam wxLoginParam) {
        String phone = wxLoginParam.getPhone();
        String token = jwtTokenUtil.generateTokenByPhone(phone);

        QueryWrapper thildQuery = new QueryWrapper();
        thildQuery.eq("openid", wxLoginParam.getOpenId());
        TThildUserAuth thildUserAuth = thildUserMapper.selectOne(thildQuery);
        if(thildUserAuth == null) {
            // 插入第三方用户表
            TThildUserAuth thildUser = new TThildUserAuth();
            thildUser.setPhone(phone);
            thildUser.setOpenid(wxLoginParam.getOpenId());
            thildUser.setSessionKey(wxLoginParam.getSessionKey());
            thildUserMapper.insert(thildUser);
        } else {
            thildUserAuth.setSessionKey(wxLoginParam.getSessionKey());
            QueryWrapper<TThildUserAuth> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("phone", wxLoginParam.getPhone());
            thildUserMapper.update(thildUserAuth, queryWrapper);
        }
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("phone", phone);
        TUser existUser = userMapper.selectOne(wrapper);
        if(existUser == null) {
            TUser user = new TUser();
            user.setUsername(phone);
            user.setNickName(initnickname+ RandomUtil.randomNumbers(9));
            user.setPassword(passwordEncoder.encode(initpwd));
            user.setPhone(phone);
            userMapper.insert(user);
        }
        return token;
    }
}
