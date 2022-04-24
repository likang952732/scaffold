package com.wwinfo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wwinfo.model.Entrypos;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wwinfo.pojo.query.ConfigQuery;
import com.wwinfo.pojo.query.EntryposQuery;
import com.wwinfo.pojo.res.EntryposRes;
import com.wwinfo.pojo.vo.EntryposAddVO;
import com.wwinfo.pojo.vo.EntryposChgVO;
import com.wwinfo.pojo.vo.EntryposDetail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * RFID进出口设置表 服务类
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
public interface EntryposService extends IService<Entrypos> {

    /**
     * 分页获取RFID进出口设置
     * @param entryposQuery
     * @return
     */
    IPage listPage(EntryposQuery entryposQuery);



    List<HashMap<String,Object>> getByParam(Map<String, Object> map);

    /**
     *
     * @param entryposAddVO
     * @return
     */
    int add(EntryposAddVO entryposAddVO);

    /**
     *
     * @param entryposChgVO
     * @return
     */
    int update(EntryposChgVO entryposChgVO);

    int delete(Long id);

    List<EntryposRes> list(EntryposQuery entryposQuery);

    EntryposDetail getDetail(Long id);

}
