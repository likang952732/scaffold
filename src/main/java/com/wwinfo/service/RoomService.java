package com.wwinfo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wwinfo.model.Room;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wwinfo.pojo.query.RoomQuery;
import com.wwinfo.pojo.vo.RoomAddVO;
import com.wwinfo.pojo.vo.RoomChgVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 库房表 服务类
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
public interface RoomService extends IService<Room> {

    /**
     * 分页获取库房
     * @param roomQuery
     * @return
     */
    IPage listPage(RoomQuery roomQuery);

    /**
     * 获取库房列表
     * @param roomQuery
     * @return
     */
    List<Room> list(RoomQuery roomQuery);

    /**
     * 通过库房名称获取库房
     * @param roomName
     * @return
     */
    Room getRoomByName(String roomName);

    /**
     * 添加库房
     * @param roomAddVO
     * @return
     */
    int add(RoomAddVO roomAddVO);

    /**
     * 编辑库房
     * @param roomChgVO
     * @return
     */
    int update(RoomChgVO roomChgVO);

    /**
     * 删除库房
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 验证库房是否上传了盘点信息
     * @param roomID
     * @return
     */
    Map<String, Object> checkRoom(Long roomID);

    /**
     *
     * @param roomID
     * @param rfids
     * @return
     */
    int invetories(Long roomID, String rfids);

}
