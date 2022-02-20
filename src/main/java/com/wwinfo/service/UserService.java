package com.wwinfo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wwinfo.model.User;
import com.wwinfo.pojo.dto.UserChgParam;
import com.wwinfo.pojo.dto.UserChgpwdParam;
import com.wwinfo.pojo.query.UserQuery;
import com.wwinfo.pojo.vo.UserAddVO;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 * 部门管理员表表 服务类
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
public interface UserService extends IService<User> {

    /**
     * 分页获取用户
     * @param userQuery
     * @return
     */
    IPage getUserPage(UserQuery userQuery);

    /**
     * 分页获取部门用户
     * @param orgID
     * @param pageSize
     * @param pageNum
     * @return
     */
    IPage getPageByOrgID(Long orgID, Integer pageSize, Integer pageNum);

    /**
     * 通过用户名称获取用户
     * @param userName
     * @return
     */
    User getUserByUserName(String userName);

    UserDetails loadUserByUserName(String userName);

    /**
     * 修改密码
     * @param userChgpwdParam
     * @return
     */
    int updatePass(UserChgpwdParam userChgpwdParam);

    /**
     * 删除用户
     * @param userName
     * @return
     */
    int delete(String userName);

    /**
     * 添加用户
     * @param userAddVO
     * @return
     */
    int add(UserAddVO userAddVO);

    /**
     * 编辑用户
     * @param userChgParam
     * @return
     */
    int update(UserChgParam userChgParam);

    /**
     * 重置密码
     * @param userName
     * @return
     */
    int resetPass(String userName);

}
