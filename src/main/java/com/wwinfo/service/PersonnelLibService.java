package com.wwinfo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wwinfo.model.PersonnelLib;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wwinfo.pojo.query.AssetQuery;
import com.wwinfo.pojo.query.PersonnelLibQuery;
import com.wwinfo.pojo.res.PersonnelLibRes;
import com.wwinfo.pojo.vo.MenuAddVO;
import com.wwinfo.pojo.vo.PersonnelLibAddVO;
import com.wwinfo.pojo.vo.PersonnelLibChgVO;

import java.util.List;

/**
 * <p>
 * 人员库表 服务类
 * </p>
 *
 * @author lk
 * @since 2022-04-15
 */
public interface PersonnelLibService extends IService<PersonnelLib> {

    /**
     * 分页获取人员库
     * @param query
     * @return
     */
    IPage listPage(PersonnelLibQuery query);

    List<PersonnelLibRes> getList(Long orgID);

    int add(PersonnelLibAddVO addVO);

    int update(PersonnelLibChgVO chgVO);

    int delete(Long id);

    /**
     *
     * @param voList   [{},{}]
     * @return
     */
    int scancodeImport(List<PersonnelLibAddVO> voList);

}
