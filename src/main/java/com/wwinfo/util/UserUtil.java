package com.wwinfo.util;

import cn.hutool.core.util.StrUtil;
import com.wwinfo.model.User;
import com.wwinfo.pojo.bo.AdminUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/*
 @Description
 *@author kang.li
 *@date 2021/3/2 9:56
 */
public class UserUtil {

    /**
     * 获取当前登录用户
     * @return
     */
    public static User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getPrincipal() instanceof AdminUserDetails){
            AdminUserDetails adminUserDetails = (AdminUserDetails)authentication.getPrincipal();
            return adminUserDetails.getUser();
        }
        return null;
    }

}
