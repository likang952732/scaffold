package com.wwinfo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwinfo.common.ExcludeEmptyQueryWrapper;
import com.wwinfo.common.exception.BusinessException;
import com.wwinfo.constant.SysConstant;
import com.wwinfo.mapper.UserMapper;
import com.wwinfo.model.Room;
import com.wwinfo.model.TAdmin;
import com.wwinfo.model.User;
import com.wwinfo.pojo.bo.AdminUserDetails;
import com.wwinfo.pojo.dto.UserChgParam;
import com.wwinfo.pojo.dto.UserChgpwdParam;
import com.wwinfo.pojo.dto.UserLoginParam;
import com.wwinfo.pojo.query.UserQuery;
import com.wwinfo.pojo.vo.UserAddVO;
import com.wwinfo.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwinfo.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * 部门管理员表表 服务实现类
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${user.defaultpassword}")
    private String defaultpassword;

    @Override
    public IPage getUserPage(UserQuery userQuery) {
        Page<User> page = new Page<>(userQuery.getPageNum(), userQuery.getPageSize());
        QueryWrapper<User> wrapper = new ExcludeEmptyQueryWrapper<>();
        wrapper.like("userName", userQuery.getUserName());
        return userMapper.selectPage(page, wrapper);
    }

    @Override
    public IPage getPageByOrgID(Long orgID, Integer pageSize, Integer pageNum) {
        Page<User> page = new Page<>(pageNum, pageSize);
        QueryWrapper<User> wrapper = new ExcludeEmptyQueryWrapper<>();
        wrapper.eq("orgID", orgID);
        return userMapper.selectPage(page, wrapper);
    }

    @Override
    public User getUserByUserName(String userName) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("userName", userName);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public UserDetails loadUserByUserName(String userName) {
        User user = getUserByUserName(userName);
        if (user == null)
            return null;
        return new AdminUserDetails(user);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updatePass(UserChgpwdParam userChgpwdParam) {
        if(!userChgpwdParam.getNewPassword().equals(userChgpwdParam.getConfirmPassword()))
            throw new BusinessException("新密码和确认密码不一致");
        UserDetails userDetails = loadUserByUserName(userChgpwdParam.getUserName());
        if(userDetails == null)
            throw new UsernameNotFoundException("用户名错误");
        if(!passwordEncoder.matches(userChgpwdParam.getOldPassword(), userDetails.getPassword())){
            throw new BusinessException("密码不正确");
        }
        User user = BeanUtil.copyProperties(userChgpwdParam, User.class);
        user.setPassword(passwordEncoder.encode(userChgpwdParam.getNewPassword()));
        user.setTimeModify(new Date());
        return userMapper.updateById(user);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delete(String userName) {
        if(StrUtil.isBlank(userName))
            throw new BusinessException("userName不能为空");
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("userName", userName);
        return userMapper.delete(wrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int add(UserAddVO userAddVO) {
        User existUser = getUserByUserName(userAddVO.getUserName());
        if(existUser != null)
            throw new BusinessException("用户名称不能重复");
        User user = BeanUtil.copyProperties(userAddVO, User.class);
        user.setPassword(passwordEncoder.encode(userAddVO.getPassword()));
        return userMapper.insert(user);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int update(UserChgParam userChgParam) {
        User user = BeanUtil.copyProperties(userChgParam, User.class);
        return userMapper.updateById(user);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int resetPass(String userName) {
        User user = getUserByUserName(userName);
        user.setPassword(passwordEncoder.encode(defaultpassword));
        return userMapper.updateById(user);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String login(UserLoginParam userLoginParam) {
        String userName = userLoginParam.getUserName();
        UserDetails userDetails = loadUserByUserName(userName);
        if(userDetails == null)
            throw new UsernameNotFoundException("用户名或密码错误");

        User user = getUserByUserName(userName);
        //处理用户锁住
        if(!passwordEncoder.matches(userLoginParam.getPassword(), userDetails.getPassword())) {
            user.setErrorTimes(user.getErrorTimes()+1);
            if(user.getErrorTimes() >= 3){
                user.setIsLocked(1);
                user.setTimeLocked(new Date());
                userMapper.updateById(user);
            }
            throw new BadCredentialsException("密码不正确");
        }

        // 验证用户锁住
        if (user.getTimeLocked() != null) {
            int lockminutes = getTimeDifference(user.getTimeLocked().toString(), DateUtil.format(new Date(), "yyyy-MM-dd hh:mm"));
            if (SysConstant.LOCKUP.equals(user.getIsLocked().toString()) && lockminutes < 5) {
                throw new UsernameNotFoundException("多次密码错误锁住,请稍后再试！");
            }
        }

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenUtil.generateToken(userDetails);

        user.setTimeLastLogin(DateUtil.date());
        user.setNeverLogin(1);
        user.setLastLoginIP(userLoginParam.getClientIP());
        user.setErrorTimes(0);
        user.setIsLocked(0);
        user.setTimeLocked(null);
        userMapper.updateById(user);
        return token;
    }

    /**
     * 计算时间差(分钟)
     */
    private int getTimeDifference(String fromDate, String toDate) {
        int diff = 0;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            long from = format.parse(fromDate).getTime();
            long to = format.parse(toDate).getTime();
            diff = (int)((to - from)/(1000 * 60));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return diff;
    }

}
