package com.wwinfo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwinfo.common.ExcludeEmptyQueryWrapper;
import com.wwinfo.common.exception.BusinessException;
import com.wwinfo.constant.SysConstant;
import com.wwinfo.mapper.OrganizeMapper;
import com.wwinfo.mapper.TConfigMapper;
import com.wwinfo.mapper.TUserRoleMapper;
import com.wwinfo.mapper.UserMapper;
import com.wwinfo.model.*;
import com.wwinfo.pojo.bo.AdminUserDetails;
import com.wwinfo.pojo.dto.UserChgParam;
import com.wwinfo.pojo.dto.UserChgpwdParam;
import com.wwinfo.pojo.dto.UserLoginParam;
import com.wwinfo.pojo.query.UserQuery;
import com.wwinfo.pojo.vo.UserAddVO;
import com.wwinfo.pojo.vo.UserResetVO;
import com.wwinfo.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwinfo.util.JwtTokenUtil;
import com.wwinfo.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.*;

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

    @Resource
    private TUserRoleMapper userRoleMapper;

    @Resource
    private TConfigMapper configMapper;

    @Resource
    private OrganizeMapper organizeMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${checkpwdrule}")
    private String checkpwdrule;


    @Override
    public IPage getUserPage(UserQuery userQuery) {
        Page<User> page = new Page<>(userQuery.getPageNum(), userQuery.getPageSize());
        if(userQuery.getUserType() == null){
            userQuery.setUserType(2);
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like(StrUtil.isNotBlank(userQuery.getUserName()), "userName", userQuery.getUserName());
        wrapper.eq("userType", userQuery.getUserType());
        wrapper.orderByDesc("timeAdd");
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
        String newPassword = userChgpwdParam.getNewPassword();
        String confirmPassword = userChgpwdParam.getConfirmPassword();
        if(!newPassword.equals(confirmPassword))
            throw new BusinessException("新密码和确认密码不一致");
        User user = userMapper.selectById(userChgpwdParam.getId());
        if(user == null)
            throw new UsernameNotFoundException("用户名错误");
        if(!passwordEncoder.matches(userChgpwdParam.getOldPassword(), user.getPassword())){
            throw new BusinessException("原密码不正确");
        }
        Map<String, Object> map = new HashMap<>();
        User currentUser = UserUtil.getCurrentUser();
        Integer firstFlag = currentUser.getFirstFlag();
        if("0".equals(firstFlag)){  //首次登陆
            Map<String, Object> param = new HashMap<>();
            param.put("fieldName", "forceChgPwd");
            TConfig config = configMapper.getConfigByMap(param);
            if(config != null && "0".equals(config.getValue())){
                checkpwd(newPassword, confirmPassword);
            }
        } else {
            checkpwd(newPassword, confirmPassword);
        }
        user.setPassword(passwordEncoder.encode(userChgpwdParam.getNewPassword()));
        user.setTimeModify(new Date());
        user.setFirstFlag(1);
        return userMapper.updateById(user);
    }

    private void checkpwd(String newPassword, String confirmPassword) {
        if(!newPassword.matches(checkpwdrule)){
            throw new BusinessException("密码长度至少8位，至少有大小写字母+数字组成");
        }
        if(!confirmPassword.matches(checkpwdrule)){
            throw new BusinessException("密码长度至少8位，至少有大小写字母+数字组成");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delete(Long id) {
        if(id == null)
            throw new BusinessException("id不能为空");
        return userMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int add(UserAddVO userAddVO) {
        User existUser = getUserByUserName(userAddVO.getUserName());
        if(existUser != null)
            throw new BusinessException("用户名称不能重复");
        if(!userAddVO.getPassword().equals(userAddVO.getConfirmPwd())){
            throw new BusinessException("密码和确认密码不一致");
        }
        User user = BeanUtil.copyProperties(userAddVO, User.class);
        user.setPassword(passwordEncoder.encode(userAddVO.getPassword()));
        return userMapper.insert(user);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int update(UserChgParam userChgParam) {
        User user = BeanUtil.copyProperties(userChgParam, User.class);
        user.setTimeModify(new Date());
        return userMapper.updateById(user);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int resetPass(UserResetVO vo) {
        if(!vo.getDefaultPwd().equals(vo.getConfirmPwd())){
            throw new BusinessException("密码和确认密码不一致");
        }
        User user = userMapper.selectById(vo.getId());
        user.setPassword(passwordEncoder.encode(vo.getDefaultPwd()));
        user.setErrorTimes(0);
        user.setTimeLocked(null);
        user.setFirstFlag(0);
        return userMapper.updateById(user);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String login(UserLoginParam userLoginParam) {
        String userName = userLoginParam.getUserName();
        UserDetails userDetails = loadUserByUserName(userName);
        User user = getUserByUserName(userName);
        if(user == null){
            return null;
        }

        Map<String, Object> map = new HashMap<>();
        //验证用户锁住
        if (user.getTimeLocked() != null) {
            int lockminutes = getTimeDifference(DateUtil.format(user.getTimeLocked(), "yyyy-MM-dd hh:mm:ss"), DateUtil.format(new Date(),"yyyy-MM-dd hh:mm"));

            int lockminutesValue = 5;   //默认锁住时长5分钟
            TConfig lockTime = configMapper.getConfigByfieldName("lockTime");
            if(lockTime != null){
                lockminutesValue = Integer.valueOf(lockTime.getValue());
            }

            if (SysConstant.LOCKUP.equals(user.getIsLocked().toString()) && lockminutes < lockminutesValue) {
                throw new BusinessException("多次密码错误锁住,请稍后再试！");
            }
        }

        //处理用户锁住
        if(!passwordEncoder.matches(userLoginParam.getPassword(), userDetails.getPassword())) {
            user.setErrorTimes(user.getErrorTimes()+1);
            String flag = "";
            int lockCount = 3;   //默认3次锁住
            TConfig lockCountconfig = configMapper.getConfigByfieldName("lockCount");
            if(lockCountconfig != null){
                lockCount = Integer.valueOf(lockCountconfig.getValue());
            }

            if(user.getErrorTimes() >= lockCount){
                user.setIsLocked(1);
                user.setTimeLocked(new Date());
                flag = "99";
            }
            userMapper.updateById(user);
            return flag;
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateRole(Long userId, List<Long> roleIds) {
        if(userId == null){
            throw new BusinessException("用户id不能为空");
        }

        if(CollUtil.isEmpty(roleIds)){
            throw new BusinessException("roleIds不能为空");
        }

        userRoleMapper.deleteByUserId(userId);

        List<TUserRole> userRoleList = new ArrayList<>();
        if(!CollUtil.isEmpty(roleIds)){
            TUserRole userRole = null;
            for (Long roleId: roleIds) {
                userRole = new TUserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(roleId);
                userRoleList.add(userRole);
            }
        }
        return userRoleMapper.addBatch(userRoleList);
    }

    @Override
    public List<TRole> getRoleByUserId(Long userId) {
        return userRoleMapper.getRoleByUserId(userId);
    }

    @Override
    public List<Organize> getOrgByUserId() {
        Long orgID = UserUtil.getCurrentUser().getOrgID();
        return organizeMapper.getOrgByUserId(orgID);
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
