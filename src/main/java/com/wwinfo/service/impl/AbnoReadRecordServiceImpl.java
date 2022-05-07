package com.wwinfo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwinfo.model.AbnoReadRecord;
import com.wwinfo.mapper.AbnoReadRecordMapper;
import com.wwinfo.model.Room;
import com.wwinfo.model.User;
import com.wwinfo.pojo.query.AbnoReadRecordQuery;
import com.wwinfo.pojo.res.AbnoReadRecordRes;
import com.wwinfo.service.AbnoReadRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwinfo.util.UserUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * <p>
 * 异常读取记录表 服务实现类
 * </p>
 *
 * @author lk
 * @since 2022-05-03
 */
@Service
public class AbnoReadRecordServiceImpl extends ServiceImpl<AbnoReadRecordMapper, AbnoReadRecord> implements AbnoReadRecordService {

    @Resource
    private AbnoReadRecordMapper abnoReadRecordMapper;

    @Override
    public IPage listPage(AbnoReadRecordQuery query) {
        User user = UserUtil.getCurrentUser();
        Optional.ofNullable(user).ifPresent(e -> query.setCanorgID(user.getOrgID()));
        Page<AbnoReadRecordRes> page = new Page<>(query.getPageNum(), query.getPageSize());
        return abnoReadRecordMapper.listPage(page, query);
    }
}
