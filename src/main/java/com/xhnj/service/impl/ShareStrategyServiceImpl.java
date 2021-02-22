package com.xhnj.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageHelper;
import com.xhnj.dao.ShareStrategyDao;
import com.xhnj.mapper.ShareStrategyMapper;
import com.xhnj.model.ShareStrategy;
import com.xhnj.model.ShareStrategyExample;
import com.xhnj.service.ShareStrategyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2021/2/22 14:00   
 */
@Service
public class ShareStrategyServiceImpl implements ShareStrategyService {
    @Resource
    private ShareStrategyMapper shareStrategyMapper;
    @Resource
    private ShareStrategyDao shareStrategyDao;


    @Override
    public List<ShareStrategy> list(Integer status, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        ShareStrategyExample example = new ShareStrategyExample();
        ShareStrategyExample.Criteria criteria = example.createCriteria();
        if(status != null) {
            criteria.andShowStatusEqualTo(status);
        }
        return shareStrategyMapper.selectByExample(example);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int create(ShareStrategy shareStrategy) {
        return shareStrategyMapper.insert(shareStrategy);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateStatus(List<Long> ids, Integer status) {
        List<ShareStrategy> list = new ArrayList<>();
        if(!CollectionUtil.isEmpty(ids)) {
            for (Long id: ids) {
                ShareStrategy shareStrategy = new ShareStrategy();
                shareStrategy.setId(id);
                shareStrategy.setShowStatus(status);
                list.add(shareStrategy);
            }
        }
        return shareStrategyDao.insertList(list);
    }
}
