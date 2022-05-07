package com.wwinfo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwinfo.common.ExcludeEmptyQueryWrapper;
import com.wwinfo.common.exception.BusinessException;
import com.wwinfo.mapper.RfidreaderMapper;
import com.wwinfo.model.Entrypos;
import com.wwinfo.mapper.EntryposMapper;
import com.wwinfo.model.Rfidreader;
import com.wwinfo.model.TConfig;
import com.wwinfo.pojo.query.EntryposQuery;
import com.wwinfo.pojo.res.EntryposRes;
import com.wwinfo.pojo.vo.EntryposAddVO;
import com.wwinfo.pojo.vo.EntryposChgVO;
import com.wwinfo.pojo.vo.EntryposDetail;
import com.wwinfo.service.EntryposService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwinfo.service.RfidreaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

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

    @Resource
    private RfidreaderMapper rfidreaderMapper;

    @Autowired
    private RfidreaderService rfidreaderService;

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

        //校验首次，最后阅读器不能为同一个
        Long endReaderID = entryposAddVO.getEndReaderID();
        if(endReaderID != null && endReaderID == entryposAddVO.getStartReaderID()){
            throw new BusinessException("首次读到的阅读器和最后读到的阅读器不能为同一个");
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

        //校验首次，最后阅读器不能为同一个
        Long endReaderID = entryposChgVO.getEndReaderID();
        if(endReaderID != null && endReaderID == entryposChgVO.getStartReaderID()){
            throw new BusinessException("首次读到的阅读器和最后读到的阅读器不能为同一个");
        }

        Entrypos entrypos = BeanUtil.copyProperties(entryposChgVO, Entrypos.class);
        entrypos.setID(entryposChgVO.getId());
        entrypos.setTimeModify(new Date());
        return entryposMapper.chgById(entrypos);

        //更新阅读器is_used
      /*  Long startReaderIDOld = exist.getStartReaderID();
        Long endReaderIDOld = exist.getEndReaderID();
        List<Long> oldidList = new ArrayList<>();
        Optional.ofNullable(startReaderIDOld).ifPresent(e -> oldidList.add(startReaderIDOld));
        Optional.ofNullable(endReaderIDOld).ifPresent(e -> oldidList.add(endReaderIDOld));
        if(CollUtil.isNotEmpty(oldidList)){
            rfidreaderMapper.updateBatchUsed(oldidList, 0);
        }

        List<Long> idList = new ArrayList<>();
        Long startReaderID = entryposChgVO.getStartReaderID();
        Long endReaderID = entryposChgVO.getEndReaderID();
        Optional.ofNullable(startReaderID).ifPresent(e -> idList.add(startReaderID));
        Optional.ofNullable(endReaderID).ifPresent(e -> idList.add(endReaderID));
        if(CollUtil.isNotEmpty(idList)){
            rfidreaderMapper.updateBatchUsed(idList, 1);
        }*/
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

    @Override
    public List<EntryposRes> list(EntryposQuery entryposQuery) {
        return entryposMapper.getList(entryposQuery);
    }

    @Override
    public EntryposDetail getDetail(Long id) {
        Entrypos entrypos = entryposMapper.selectById(id);
        EntryposDetail entryposDetail = BeanUtil.copyProperties(entrypos, EntryposDetail.class);
        //查询所有未使用的列表
        List<Rfidreader> rfidreaders = rfidreaderService.listAll(0);
        List<Rfidreader> endReadList = rfidreaderService.listAll(0);
        Rfidreader startRead = rfidreaderMapper.selectById(entrypos.getStartReaderID());
        Long endReaderID = entrypos.getEndReaderID();
        if(endReaderID != null){
            Rfidreader endRead = rfidreaderMapper.selectById(endReaderID);
            Optional.ofNullable(endRead).ifPresent(e -> endReadList.add(endRead));
        }
        rfidreaders.add(startRead);
        entryposDetail.setStartReadList(rfidreaders);
        entryposDetail.setEndReadList(endReadList);
        return entryposDetail;
    }
}
