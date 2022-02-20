package com.wwinfo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwinfo.common.ExcludeEmptyQueryWrapper;
import com.wwinfo.common.exception.BusinessException;
import com.wwinfo.model.Room;
import com.wwinfo.mapper.RoomMapper;
import com.wwinfo.pojo.query.RoomQuery;
import com.wwinfo.pojo.vo.RoomAddVO;
import com.wwinfo.pojo.vo.RoomChgVO;
import com.wwinfo.service.RoomService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public IPage listPage(RoomQuery roomQuery) {
        Page<Room> page = new Page<>(roomQuery.getPageNum(), roomQuery.getPageSize());
        QueryWrapper<Room> wrapper = new ExcludeEmptyQueryWrapper<>();
        wrapper.like("roomName", roomQuery.getRoomName());
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
        return roomMapper.updateById(room);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delete(Long id) {
        if(id == null)
            throw new BusinessException("id不能重复");
        return roomMapper.deleteById(id);
    }
}
