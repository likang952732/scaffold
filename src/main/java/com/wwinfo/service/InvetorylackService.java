package com.wwinfo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wwinfo.model.Invetorylack;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wwinfo.pojo.query.InvetorylackQuery;

import java.util.Map;

/**
 * <p>
 * 资产盘点缺少表 服务类
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
public interface InvetorylackService extends IService<Invetorylack> {

    /**
     * 分页获取资产盘点缺少
     * @param invetorylackQuery
     * @return
     */
    IPage listPage(InvetorylackQuery invetorylackQuery);

    /**
     * 通过任务id获取判断结果
     * @param taskID
     * @return
     */
    Map<String, Object> getResultByTaskId(Long taskID);

}
