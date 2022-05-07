package com.wwinfo.service.impl;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwinfo.common.CommonResult;
import com.wwinfo.mapper.InvetoryassetMapper;
import com.wwinfo.model.Invetoryasset;
import com.wwinfo.model.Invetorylack;
import com.wwinfo.mapper.InvetorylackMapper;
import com.wwinfo.model.Room;
import com.wwinfo.model.User;
import com.wwinfo.pojo.query.InvetorylackQuery;
import com.wwinfo.pojo.res.InvetorylackRes;
import com.wwinfo.service.InvetorylackService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwinfo.util.UserUtil;
import org.springframework.security.web.PortResolverImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <p>
 * 资产盘点缺少表 服务实现类
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Service
public class InvetorylackServiceImpl extends ServiceImpl<InvetorylackMapper, Invetorylack> implements InvetorylackService {

    @Resource
    private InvetorylackMapper invetorylackMapper;

    @Resource
    private InvetoryassetMapper invetoryassetMapper;

    @Override
    public IPage listPage(InvetorylackQuery query) {
        Page<InvetorylackRes> page = new Page<>(query.getPageNum(), query.getPageSize());
        User user = UserUtil.getCurrentUser();
        Optional.ofNullable(user).ifPresent(e -> query.setOrgID(user.getOrgID()));
        return invetorylackMapper.page(page, query);
    }

    @Override
    public List<Map<String, Object>> getResultByTaskId(Long taskID) {
        JSONObject result = new JSONObject();
        List<Map<String, Object>> lackMap = invetorylackMapper.getInvetoryInfoByTaskId(taskID);
        //List<Map<String, Object>> overflowMap = invetoryassetMapper.getOverflowByTaskId(taskID);
        return lackMap;
    }

}
