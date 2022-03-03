package com.wwinfo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wwinfo.model.Invetorytask;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wwinfo.pojo.dto.InvTaskCancelParam;
import com.wwinfo.pojo.query.InvetorytaskQuery;
import com.wwinfo.pojo.vo.InvetorytaskAddVO;
import com.wwinfo.pojo.vo.InvetorytaskChgVO;

import java.util.List;

/**
 * <p>
 * 资产盘点任务表 服务类
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
public interface InvetorytaskService extends IService<Invetorytask> {

    /**
     * 分页获取资产盘点任务
     * @param invetorytaskQuery
     * @return
     */
    IPage listPage(InvetorytaskQuery invetorytaskQuery);

    /**
     *
     * @param assetID
     * @return
     */
    List<Invetorytask> getByAssetID(Long assetID);

    /**
     * 添加资产盘点任务
     * @param invetorytaskAddVO
     * @return
     */
    int add(InvetorytaskAddVO invetorytaskAddVO);

    /**
     * 编辑资产盘点任务
     * @param invetorytaskChgVO
     * @return
     */
    int update(InvetorytaskChgVO invetorytaskChgVO);

    /**
     * 盘点任务结束或取消
     * @param invTaskCancelParam
     * @return
     */
    int cancel(InvTaskCancelParam invTaskCancelParam);

}
