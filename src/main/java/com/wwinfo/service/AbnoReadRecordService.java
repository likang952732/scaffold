package com.wwinfo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wwinfo.model.AbnoReadRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wwinfo.pojo.query.AbnoReadRecordQuery;

/**
 * <p>
 * 异常读取记录表 服务类
 * </p>
 *
 * @author lk
 * @since 2022-05-03
 */
public interface AbnoReadRecordService extends IService<AbnoReadRecord> {

    IPage listPage(AbnoReadRecordQuery query);
}
