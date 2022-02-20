package com.wwinfo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wwinfo.model.Organize;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wwinfo.pojo.bo.OrganizeNode;
import com.wwinfo.pojo.query.OrganizeQuery;
import com.wwinfo.pojo.vo.OrganizeAddVO;
import com.wwinfo.pojo.vo.OrganizeChgVO;

import java.util.List;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
public interface OrganizeService extends IService<Organize> {

    /**
     * 分页获取父级部门
     * @param organizeQuery
     * @return
     */
    IPage listPage(OrganizeQuery organizeQuery);

    /**
     * 获取父级部门列表
     * @return
     */
    List<Organize> list();

    /**
     * 获取指定部门的下级部门列表
     * @param orgID
     * @param pageSize
     * @param pageNum
     * @return
     */
    IPage nextLevel(Long orgID, Integer pageSize, Integer pageNum);

    /**
     * 树形结构返回所有部门列表
     * @return
     */
    List<OrganizeNode> treeList();

    /**
     * 添加部门
     * @param organizeAddVO
     * @return
     */
    int add(OrganizeAddVO organizeAddVO);

    /**
     * 编辑部门
     * @param organizeChgVO
     * @return
     */
    int update(OrganizeChgVO organizeChgVO);

    /**
     * 删除部门
     * @param orgID
     * @return
     */
    int delete(Long orgID);

}
