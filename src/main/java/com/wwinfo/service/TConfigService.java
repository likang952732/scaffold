package com.wwinfo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wwinfo.model.TConfig;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wwinfo.pojo.query.ConfigQuery;
import com.wwinfo.pojo.vo.ConfigAddVO;
import com.wwinfo.pojo.vo.ConfigChgVO;

/**
 * <p>
 * 系统配置表 服务类
 * </p>
 *
 * @author lk
 * @since 2022-02-17
 */
public interface TConfigService extends IService<TConfig> {

    /**
     *
     * @param configQuery
     * @return
     */
    IPage listPage(ConfigQuery configQuery);

    /**
     *
     * @param configAddVO
     * @return
     */
    int add(ConfigAddVO configAddVO);

    /**
     *
     * @param configChgVO
     * @return
     */
    int update(ConfigChgVO configChgVO);

    /**
     *
     * @param id
     * @return
     */
    int delete(Long id);
}
