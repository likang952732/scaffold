package com.wwinfo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwinfo.common.ExcludeEmptyQueryWrapper;
import com.wwinfo.common.exception.BusinessException;
import com.wwinfo.mapper.AssetMapper;
import com.wwinfo.mapper.EntryposMapper;
import com.wwinfo.model.Asset;
import com.wwinfo.model.Entrypos;
import com.wwinfo.model.Room;
import com.wwinfo.mapper.RoomMapper;
import com.wwinfo.pojo.query.RoomQuery;
import com.wwinfo.pojo.vo.RoomAddVO;
import com.wwinfo.pojo.vo.RoomChgVO;
import com.wwinfo.service.AssetService;
import com.wwinfo.service.RoomService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 库房表 服务实现类
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements RoomService {

    @Resource
    private RoomMapper roomMapper;

    @Resource
    private AssetMapper assetMapper;

    @Resource
    private EntryposMapper entryposMapper;

    @Override
    public IPage listPage(RoomQuery roomQuery) {
        Page<Room> page = new Page<>(roomQuery.getPageNum(), roomQuery.getPageSize());
        QueryWrapper<Room> wrapper = new ExcludeEmptyQueryWrapper<>();
        wrapper.like("roomName", roomQuery.getRoomName());
        wrapper.orderByDesc("timeAdd");
        return roomMapper.selectPage(page, wrapper);
    }

    @Override
    public List<Room> list(RoomQuery roomQuery) {
        QueryWrapper<Room> wrapper = new ExcludeEmptyQueryWrapper<>();
        wrapper.like("roomName", roomQuery.getRoomName());
        return roomMapper.selectList(wrapper);
    }

    @Override
    public Room getRoomByName(String roomName) {
        QueryWrapper<Room> wrapper = new ExcludeEmptyQueryWrapper<>();
        wrapper.eq("roomName", roomName);
        return roomMapper.selectOne(wrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int add(RoomAddVO roomAddVO) {
        Room existRoom = getRoomByName(roomAddVO.getRoomName());
        if(existRoom != null)
            throw new BusinessException("库房名称不能重复");
        Room room = BeanUtil.copyProperties(roomAddVO, Room.class);
        return roomMapper.insert(room);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int update(RoomChgVO roomChgVO) {
        Room existRoom = getRoomByName(roomChgVO.getRoomName());
        if(existRoom != null && roomChgVO.getId() != existRoom.getID())
            throw new BusinessException("库房名称不能重复");
        Room room = BeanUtil.copyProperties(roomChgVO, Room.class);
        room.setID(roomChgVO.getId());
        return roomMapper.updateById(room);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delete(Long id) {
        if(id == null)
            throw new BusinessException("id不能为空");
        //如果库房当前有资产或在出入口设置中被关联，则不能删除
        QueryWrapper<Asset> wrapper = new ExcludeEmptyQueryWrapper<>();
        wrapper.eq("roomID", id);
        List<Asset> list = assetMapper.selectList(wrapper);
        if(CollUtil.isNotEmpty(list)){
            throw new BusinessException("该库房存在资产，不能删除");
        }

        QueryWrapper<Entrypos> enWrapper = new ExcludeEmptyQueryWrapper<>();
        enWrapper.eq("roomID", id);
        List<Entrypos> entryposList = entryposMapper.selectList(enWrapper);
        if(CollUtil.isNotEmpty(entryposList)){
            throw new BusinessException("该库房已在出入口设置中被关联，不能删除");
        }

        return roomMapper.deleteById(id);
    }

    @Override
    public Map<String, Object> checkRoom(Long roomID) {
        if(roomID == null) {
            throw new BusinessException("roomID不能为空");
        }
        Map<String, Object> result = new HashMap<>();
        Room room = getRoomByRoomID(roomID);
        result.put("ID", roomID);
        result.put("invetory", 0);
        if(room != null){
            result.put("invetory", 1);
        }
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int invetories(Long roomID, String rfids) {
        if(roomID == null || StrUtil.isBlank(rfids)){
            throw new BusinessException("roomID, rfids不能为空");
        }
        Room room = getRoomByRoomID(roomID);
        if(room != null){

        }

        return 0;
    }

    private Room getRoomByRoomID(Long roomID){
        QueryWrapper<Room> wrapper = new ExcludeEmptyQueryWrapper<>();
        wrapper.eq("roomID", roomID);
        return roomMapper.selectOne(wrapper);
    }

}
