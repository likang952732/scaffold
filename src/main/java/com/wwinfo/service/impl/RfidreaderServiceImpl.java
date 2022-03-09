package com.wwinfo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwinfo.common.ExcludeEmptyQueryWrapper;
import com.wwinfo.common.exception.BusinessException;
import com.wwinfo.mapper.EntryposMapper;
import com.wwinfo.model.Entrypos;
import com.wwinfo.model.Rfidreader;
import com.wwinfo.mapper.RfidreaderMapper;
import com.wwinfo.pojo.query.RfidreaderQuery;
import com.wwinfo.pojo.vo.RfidreaderAddVO;
import com.wwinfo.pojo.vo.RfidreaderChgParam;
import com.wwinfo.service.RfidreaderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * RFID阅读器表 服务实现类
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Service
public class RfidreaderServiceImpl extends ServiceImpl<RfidreaderMapper, Rfidreader> implements RfidreaderService {

    @Resource
    private RfidreaderMapper rfidreaderMapper;

    @Resource
    private EntryposMapper entryposMapper;

    @Override
    public IPage listPage(RfidreaderQuery rfidreaderQuery) {
        Page<Rfidreader>  page = new Page<>(rfidreaderQuery.getPageNum(), rfidreaderQuery.getPageSize());
        QueryWrapper<Rfidreader> wrapper = new ExcludeEmptyQueryWrapper<>();
        wrapper.like("readerName", rfidreaderQuery.getReaderName());
        wrapper.eq("interfaceType", rfidreaderQuery.getInterfaceType());
        return rfidreaderMapper.selectPage(page, wrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int add(RfidreaderAddVO rfidreaderAddVO) {
        Rfidreader existReader = getRfidreaderCondition(rfidreaderAddVO.getReaderName(), rfidreaderAddVO.getReaderIP());
        if(existReader != null)
            throw new BusinessException("阅读器名称或阅读器IP地址不能重复");
        Rfidreader rfidreader = BeanUtil.copyProperties(rfidreaderAddVO, Rfidreader.class);
        rfidreader.setLastStatus(0);
        return rfidreaderMapper.insert(rfidreader);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int update(RfidreaderChgParam rfidreaderChgParam) {
        Rfidreader existReader = getRfidreaderCondition(rfidreaderChgParam.getReaderName(), rfidreaderChgParam.getReaderIP());
        if(existReader != null && rfidreaderChgParam.getId() != existReader.getID())
            throw new BusinessException("阅读器名称或阅读器IP地址不能重复");
        Rfidreader rfidreader = BeanUtil.copyProperties(rfidreaderChgParam, Rfidreader.class);
        rfidreader.setID(rfidreaderChgParam.getId());
        return rfidreaderMapper.updateById(rfidreader);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int update(Rfidreader rfidreader) {
        return rfidreaderMapper.updateById(rfidreader);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delete(Long id) {
        if(id == null)
            throw new BusinessException("id不能为空");
        //校验阅读器是否在出入点配置中使用
        QueryWrapper<Entrypos> wrapper = new ExcludeEmptyQueryWrapper<>();
        wrapper.eq("startReaderID", id)
                .or()
                .eq("endReaderID", id);
        List<Entrypos> entryposList = entryposMapper.selectList(wrapper);
        if(CollUtil.isNotEmpty(entryposList)){
            throw new BusinessException("该阅读器已在出入点配置中使用,不能删除");
        }
        return rfidreaderMapper.deleteById(id);
    }

    @Override
    public Rfidreader getByReaderName(String readerName) {
        QueryWrapper<Rfidreader> wrapper = new ExcludeEmptyQueryWrapper<>();
        wrapper.eq("readerName", readerName);
        return rfidreaderMapper.selectOne(wrapper);
    }

    @Override
    public List<HashMap<String,Object>> getAllReader() {
        return rfidreaderMapper.getAllReader();
    }


    private Rfidreader getRfidreaderCondition(String readerName, String readerIP){
        QueryWrapper<Rfidreader> wrapper = new ExcludeEmptyQueryWrapper<>();
        wrapper.eq("readerName", readerName)
                .or()
                .eq("readerIP", readerIP);

        return rfidreaderMapper.selectOne(wrapper);
    }

}
