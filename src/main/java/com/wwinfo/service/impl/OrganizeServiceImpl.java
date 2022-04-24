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
    public List<Organize> list(OrganizeQuery query){
        QueryWrapper<Organize> wrapper = new ExcludeEmptyQueryWrapper<>();
        if(query.getOrgLevel() == null){
            wrapper.eq("orgLevel", 0);
        } else {
            wrapper.eq("orgLevel", query.getOrgLevel());
        }
        wrapper.like("orgName", query.getOrgName());
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
    public List<OrganizeNode> treeList(String orgName) {
        QueryWrapper<Organize> wrapper = new QueryWrapper<>();
        wrapper.like(StrUtil.isNotBlank(orgName), "orgName", orgName);
        List<Organize> organizeList = organizeMapper.selectList(wrapper);
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
        organize.setOrgLevel(0);
        Long upOrgID = organize.getUpOrgID();
        if(upOrgID != null){
            Organize upOrg = organizeMapper.selectById(upOrgID);
            organize.setOrgLevel(upOrg.getOrgLevel()+1);
        }
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
        List<Organize> childList = organizeMapper.getChildByOrgID(orgID);
        if(CollUtil.isNotEmpty(childList)){
            throw new BusinessException("请先删除子部门");
        }

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

    @Override
    public List<OrganizeNode> getUpTreeByChild(Long orgID) {
        List<Organize> organizeList = organizeMapper.getUpTreeByChild(orgID);
        List<OrganizeNode> result = organizeList.stream()
                .filter(org -> org.getOrgLevel() == 0)
                .map(org ->covertOrganizeNode(org, organizeList))
                .collect(Collectors.toList());
        if(result.size() == 1 && CollUtil.isEmpty(result.get(0).getChildren())){
            return result;
        }
        List<OrganizeNode> filterResult = result.stream().filter(e -> CollUtil.isNotEmpty(e.getChildren())).collect(Collectors.toList());
        return filterResult;
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
