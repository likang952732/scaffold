package com.wwinfo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwinfo.common.ExcludeEmptyQueryWrapper;
import com.wwinfo.common.exception.BusinessException;
import com.wwinfo.model.TSyslog;
import com.wwinfo.mapper.TSyslogMapper;
import com.wwinfo.pojo.query.SysLogQuery;
import com.wwinfo.service.TSyslogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>
 * 系统日志表 服务实现类
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Slf4j
@Service
public class TSyslogServiceImpl extends ServiceImpl<TSyslogMapper, TSyslog> implements TSyslogService {

    @Resource
    private TSyslogMapper syslogMapper;

    @Override
    public IPage listPage(SysLogQuery sysLogQuery) {
        Page<TSyslog> page = new Page<>(sysLogQuery.getPageNum(), sysLogQuery.getPageSize());
        QueryWrapper<TSyslog> wrapper = new ExcludeEmptyQueryWrapper<>();
        wrapper.like("userName", sysLogQuery.getUserName());
        wrapper.eq("operate", sysLogQuery.getOperate());
        wrapper.eq("objectType", sysLogQuery.getObjectType());
        wrapper.eq("objectName", sysLogQuery.getObjectName());
        wrapper.ge("timeOperate", sysLogQuery.getStartDate());
        wrapper.le("timeOperate", sysLogQuery.getEndDate());
        return syslogMapper.selectPage(page, wrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int add(TSyslog syslog) {
        return syslogMapper.insert(syslog);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delete(String delDate) {
        try {
            Date beforeDate = getTime(new Date(), -3);   //获取3个月之前的时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date enDate = sdf.parse(delDate);
            if(!enDate.before(beforeDate)){
                throw new BusinessException("删除时间必需早于三个月");
            }
        } catch (ParseException e) {
            log.error("删除日志异常: {}", e);
        }
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.le("timeOperate", delDate);
        return syslogMapper.delete(wrapper);
    }

    /**
     * 获取当前日期之前或之后几个月的日期,n < 0表示当前日期之前几个月的时间
     * @param date
     * @param n
     * @return
     */
    private Date getTime(Date date, int n){
        if(date == null)
            date = new Date();
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(date);//把当前时间赋给日历
        calendar.add(calendar.MONTH, n); //设置为前n月
        return calendar.getTime(); //得到前n月的时间
    }
}
