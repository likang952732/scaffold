package com.wwinfo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwinfo.common.exception.BusinessException;
import com.wwinfo.model.PersonnelLib;
import com.wwinfo.mapper.PersonnelLibMapper;
import com.wwinfo.pojo.query.PersonnelLibQuery;
import com.wwinfo.pojo.res.PersonnelLibRes;
import com.wwinfo.pojo.vo.PersonnelLibAddVO;
import com.wwinfo.pojo.vo.PersonnelLibChgVO;
import com.wwinfo.service.PersonnelLibService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwinfo.util.UserUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.swing.text.html.Option;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 人员库表 服务实现类
 * </p>
 *
 * @author lk
 * @since 2022-04-15
 */
@Service
public class PersonnelLibServiceImpl extends ServiceImpl<PersonnelLibMapper, PersonnelLib> implements PersonnelLibService {

    @Resource
    private PersonnelLibMapper personnelLibMapper;

    @Override
    public IPage listPage(PersonnelLibQuery query) {
        Page<PersonnelLibRes> page = new Page<>(query.getPageNum(), query.getPageSize());
        Long orgID = UserUtil.getCurrentUser().getOrgID();
        Optional.ofNullable(query.getOrgID()).ifPresent(e -> query.setCanorgID(query.getOrgID()));
        if(query.getOrgID() == null){
            query.setOrgID(orgID);
        }
        return personnelLibMapper.listPage(page, query);
    }

    @Override
    public List<PersonnelLibRes> getList(Long orgID) {
        return personnelLibMapper.getList(orgID);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int add(PersonnelLibAddVO addVO) {
        checkRepeat(addVO.getJobNo(), addVO.getPhone(), "add", null);
        PersonnelLib personnelLib = BeanUtil.copyProperties(addVO, PersonnelLib.class);
        return personnelLibMapper.insert(personnelLib);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int update(PersonnelLibChgVO chgVO) {
        checkRepeat(chgVO.getJobNo(), chgVO.getPhone(), "chg", chgVO.getId());
        PersonnelLib personnelLib = BeanUtil.copyProperties(chgVO, PersonnelLib.class);
        personnelLib.setUpdateTime(new Date());
        return personnelLibMapper.updateById(personnelLib);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delete(Long id) {
        if(id == null){
            throw new BusinessException("id不能为空");
        }
        return personnelLibMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int scancodeImport(List<PersonnelLibAddVO> voList) {
        return 0;
    }

    private void checkRepeat(String jobNo, String phone, String operation, Long id){
        QueryWrapper<PersonnelLib> wrapper = new QueryWrapper<>();
        wrapper.eq("job_no", jobNo);
        PersonnelLib exist = personnelLibMapper.selectOne(wrapper);
        if("add".equals(operation) && exist != null){
            throw new BusinessException("工号不能重复");
        } else if("chg".equals(operation)){
            if(exist != null && id != exist.getId()){
                throw new BusinessException("工号不能重复");
            }
        }
        QueryWrapper<PersonnelLib> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("phone", phone);
        exist = personnelLibMapper.selectOne(wrapper2);
        if("add".equals(operation) && exist != null){
            throw new BusinessException("手机号不能重复");
        } else if("chg".equals(operation)){
            if(exist != null && id != exist.getId()){
                throw new BusinessException("手机号不能重复");
            }
        }
    }
}
