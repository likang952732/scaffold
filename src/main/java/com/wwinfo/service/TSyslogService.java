package com.wwinfo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wwinfo.model.TLog;
import com.wwinfo.model.TSyslog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wwinfo.pojo.query.SysLogQuery;

/**
 * <p>
 * 系统日志表 服务类
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
public interface TSyslogService extends IService<TSyslog> {

    /**
     * 分页获取日志
     * @param sysLogQuery
     * @return
     */
    IPage listPage(SysLogQuery sysLogQuery);

    /**
     * 添加日志
     * @param syslog
     * @return
     */
    int add(TSyslog syslog);

    /**
     * 删除日志
     * @param delDate
     * @return
     */
    int delete(String delDate);
}
