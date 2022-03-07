package com.wwinfo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwinfo.common.ExcludeEmptyQueryWrapper;
import com.wwinfo.common.exception.BusinessException;
import com.wwinfo.mapper.AssetMapper;
import com.wwinfo.mapper.InvetorytaskMapper;
import com.wwinfo.mapper.UserMapper;
import com.wwinfo.model.Invetorytask;
import com.wwinfo.model.Organize;
import com.wwinfo.mapper.OrganizeMapper;
import com.wwinfo.model.User;
import com.wwinfo.pojo.bo.OrganizeNode;
import com.wwinfo.pojo.query.OrganizeQuery;
import com.wwinfo.pojo.res.AssetApiRes;
import com.wwinfo.pojo.vo.OrganizeAddVO;
import com.wwinfo.pojo.vo.OrganizeChgVO;
import com.wwinfo.service.OrganizeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Service
public class OrganizeServiceImpl extends ServiceImpl<OrganizeMapper, Organize> implements OrganizeService {

    @Resource
    private OrganizeMapper organizeMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private AssetMapper assetMapper;

    @Resource
    private InvetorytaskMapper invetorytaskMapper;


    @Override
    public IPage listPage(OrganizeQuery organizeQuery) {
        Page<Organize> page = new Page<>(organizeQuery.getPageNum(), organizeQuery.getPageSize());
        QueryWrapper<Organize> wrapper = new ExcludeEmptyQueryWrapper<>();
        wrapper.eq("orgLevel", 0);
        return organizeMapper.selectPage(page, wrapper);
    }

    @Override
    public List<Organize> list(Integer orgLevel){
        QueryWrapper<Organize> wrapper = new ExcludeEmptyQueryWrapper<>();
        wrapper.eq("orgLevel", orgLevel);
        return organizeMapper.selectList(wrapper);
    }

    @Override
    public IPage nextLevel(Long orgID, Integer pageSize, Integer pageNum) {
        Page<Organize> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Organize> wrapper = new ExcludeEmptyQueryWrapper<>();
        wrapper.eq("upOrgID", orgID);
        return organizeMapper.selectPage(page, wrapper);
    }

    @Override
    public List<OrganizeNode> treeList() {
        List<Organize> organizeList = organizeMapper.selectList(null);
        List<OrganizeNode> result = organizeList.stream()
                .filter(org -> org.getOrgLevel() == 0)
                .map(org ->covertOrganizeNode(org, organizeList))
                .collect(Collectors.toList());
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int add(OrganizeAddVO organizeAddVO) {
        //校验orgName唯一性
        Organize exist = getOrganizeByOrgName(organizeAddVO.getOrgName());
        if(exist != null){
            throw new BusinessException("部门名称不能重复");
        }
        Organize organize = BeanUtil.copyProperties(organizeAddVO, Organize.class);
        return organizeMapper.insert(organize);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int update(OrganizeChgVO organizeChgVO) {
        //校验orgName唯一性
        Organize exist = getOrganizeByOrgName(organizeChgVO.getOrgName());
        if(exist != null && exist.getOrgID() != organizeChgVO.getOrgID()){
            throw new BusinessException("部门名称不能重复");
        }
        Organize organize = BeanUtil.copyProperties(organizeChgVO, Organize.class);
        return organizeMapper.updateById(organize);
    }

    private Organize getOrganizeByOrgName(String orgName){
        QueryWrapper<Organize> wrapper = new ExcludeEmptyQueryWrapper<>();
        wrapper.eq("orgName", orgName);
        return organizeMapper.selectOne(wrapper);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delete(Long orgID) {
        if(orgID == null)
            throw new BusinessException("orgID不能为空");
        QueryWrapper<User> wrapper = new ExcludeEmptyQueryWrapper<>();
        wrapper.eq("orgID", orgID);
        List<User> userList = userMapper.selectList(wrapper);
        if(CollUtil.isNotEmpty(userList)) {
            throw new BusinessException("该部门下存在用户，不能删除");
        }

        //校验是否存在资产
        List<Long> orgList = new ArrayList<>();
        orgList.add(orgID);
        List<AssetApiRes> assetList = assetMapper.getAssetListByOrgs(orgList);
        if(CollUtil.isNotEmpty(assetList)){
            throw new BusinessException("该部门下存在资产，不能删除");
        }

        //校验是否存在任务
        List<Invetorytask> taskList = invetorytaskMapper.getTaskByOrgIds(orgList);
        if(CollUtil.isNotEmpty(taskList)){
            throw new BusinessException("该部门下存在盘点任务，不能删除");
        }
        return organizeMapper.deleteById(orgID);
    }

    private OrganizeNode covertOrganizeNode(Organize organize, List<Organize> organizeList) {
        OrganizeNode node = new OrganizeNode();
        BeanUtils.copyProperties(organize, node);
        List<OrganizeNode> childrens = organizeList.stream()
                .filter(subOrg ->node.getOrgID().equals(subOrg.getUpOrgID()))
                .map(subOrg -> covertOrganizeNode(subOrg, organizeList))
                .collect(Collectors.toList());
        node.setChildren(childrens);
        return node;
    }

}
