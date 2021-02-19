package com.xhnj.service.impl;

import com.github.pagehelper.PageHelper;
import com.xhnj.dao.HomeAdvertiseDao;
import com.xhnj.mapper.HomeAdvertiseMapper;
import com.xhnj.model.HomeAdvertise;
import com.xhnj.model.HomeAdvertiseExample;
import com.xhnj.service.IHomeAdvertiseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2021/2/19 14:26   
 */
@Service
public class HomeAdvertiseServiceImpl implements IHomeAdvertiseService {
    @Resource
    HomeAdvertiseMapper homeAdvertiseMapper;
    @Resource
    HomeAdvertiseDao homeAdvertiseDao;

    @Override
    public List<HomeAdvertise> list(Integer status, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        HomeAdvertiseExample example = new HomeAdvertiseExample();
        HomeAdvertiseExample.Criteria criteria = example.createCriteria();
        if(status != null) {
            criteria.andStatusEqualTo(status);
        }
        return homeAdvertiseMapper.selectByExample(example);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public int create(HomeAdvertise homeAdvertise) {
        return homeAdvertiseMapper.insert(homeAdvertise);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public int update(HomeAdvertise homeAdvertise) {
        return homeAdvertiseMapper.updateByPrimaryKeySelective(homeAdvertise);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public int delete(List<Long> ids) {
        int count = ids == null ? 0 : ids.size();
        homeAdvertiseDao.deleteByIds(ids);
        return count;
    }
}
