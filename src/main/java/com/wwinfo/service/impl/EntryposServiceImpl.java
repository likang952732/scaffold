package com.wwinfo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwinfo.common.ExcludeEmptyQueryWrapper;
import com.wwinfo.common.exception.BusinessException;
import com.wwinfo.model.Entrypos;
import com.wwinfo.mapper.EntryposMapper;
import com.wwinfo.model.TConfig;
import com.wwinfo.pojo.query.EntryposQuery;
import com.wwinfo.pojo.res.EntryposRes;
import com.wwinfo.pojo.vo.EntryposAddVO;
import com.wwinfo.pojo.vo.EntryposChgVO;
import com.wwinfo.service.EntryposService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * RFID进出口设置表 服务实现类
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Service
public class EntryposServiceImpl extends ServiceImpl<EntryposMapper, Entrypos> implements EntryposService {

    @Resource
    private EntryposMapper entryposMapper;

    @Override
    public IPage listPage(EntryposQuery entryposQuery) {
        Page<EntryposRes> page = new Page<>(entryposQuery.getPageNum(), entryposQuery.getPageSize());
        return entryposMapper.Page(page, entryposQuery);
    }

    @Override
    public List<HashMap<String, Object>> getByParam(Map<String, Object> map) {
        return entryposMapper.getByParam(map);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int add(EntryposAddVO entryposAddVO) {
        Entrypos exist = getEntryposByPosName(entryposAddVO.getPosName());
        if(exist != null){
            throw new BusinessException("位置名称不能重复");
        }
        Entrypos entrypos = BeanUtil.copyProperties(entryposAddVO, Entrypos.class);
        return entryposMapper.insert(entrypos);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int update(EntryposChgVO entryposChgVO) {
        Entrypos exist = getEntryposByPosName(entryposChgVO.getPosName());
        if(exist != null && exist.getID() != entryposChgVO.getId()){
            throw new BusinessException("位置名称不能重复");
        }
        Entrypos entrypos = BeanUtil.copyProperties(entryposChgVO, Entrypos.class);
        entrypos.setID(entryposChgVO.getId());
        return entryposMapper.updateById(entrypos);
    }

    private Entrypos getEntryposByPosName(String posName){
        QueryWrapper<Entrypos> wrapper = new ExcludeEmptyQueryWrapper<>();
        wrapper.like("posName", posName);
         return entryposMapper.selectOne(wrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delete(Long id) {
        if(id == null){
            throw new BusinessException("id不能重复");
        }
        return entryposMapper.deleteById(id);
    }
}
