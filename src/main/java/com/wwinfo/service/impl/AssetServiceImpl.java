package com.wwinfo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwinfo.common.BusinValidatorContext;
import com.wwinfo.common.ExcludeEmptyQueryWrapper;
import com.wwinfo.common.exception.BusinessException;
import com.wwinfo.component.ValidateProcessor;
import com.wwinfo.constant.SysConstant;
import com.wwinfo.constant.ValidateTypeConstant;
import com.wwinfo.mapper.AssetoutregMapper;
import com.wwinfo.mapper.RfidAssetMapper;
import com.wwinfo.model.*;
import com.wwinfo.mapper.AssetMapper;
import com.wwinfo.pojo.bo.AssetStatusExcel;
import com.wwinfo.pojo.dto.AssetDestoryParam;
import com.wwinfo.pojo.dto.AssetQueryParam;
import com.wwinfo.pojo.dto.AssetReturnVParam;
import com.wwinfo.pojo.dto.BlackListVParam;
import com.wwinfo.pojo.query.AssetQuery;
import com.wwinfo.pojo.res.AssetRes;
import com.wwinfo.pojo.vo.AssetAddVO;
import com.wwinfo.pojo.vo.AssetChgVO;
import com.wwinfo.pojo.vo.BindRFIDVO;
import com.wwinfo.service.AssetService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwinfo.service.InvetorytaskService;
import com.wwinfo.service.RfidMappingService;
import com.wwinfo.service.RfidrecordService;
import com.wwinfo.util.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 资产表 服务实现类
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Slf4j
@Service
public class AssetServiceImpl extends ServiceImpl<AssetMapper, Asset> implements AssetService {

    @Autowired
    private ValidateProcessor validateProcessor;

    @Resource
    private AssetMapper assetMapper;

    @Resource
    private AssetoutregMapper assetoutregMapper;

    @Resource
    private RfidAssetMapper rfidAssetMapper;

    @Resource
    private RfidMappingService rfidMappingService;

    @Resource
    private RfidrecordService rfidrecordService;

    @Resource
    private InvetorytaskService invetorytaskService;

    @Override
    public IPage listPage(AssetQuery assetQuery) {
        User user = UserUtil.getCurrentUser();
        assetQuery.setOrgID(user.getOrgID());
        Page<AssetRes> page = new Page<>(assetQuery.getPageNum(), assetQuery.getPageSize());
        return assetMapper.page(page, assetQuery);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int add(AssetAddVO assetAddVO) {
        Asset existAsset = getAssetByParam(assetAddVO.getName(), assetAddVO.getAssetNo(), assetAddVO.getRfidNo());
        if(existAsset != null){
            throw new BusinessException("资产名称,资产编号,RFID编号不能重复");
        }
        Asset asset = BeanUtil.copyProperties(assetAddVO, Asset.class);
        asset.setDelStatus(SysConstant.DELSTATUS_NOT);
        return assetMapper.insert(asset);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int update(AssetChgVO assetChgVO) {
        QueryWrapper<Asset> wrapper = new ExcludeEmptyQueryWrapper<>();
        wrapper.eq("name", assetChgVO.getName())
                .or()
                .eq("rfidNo", assetChgVO.getRfidNo());
        Asset existAsset = assetMapper.selectOne(wrapper);
        if(existAsset != null && assetChgVO.getId() != existAsset.getID()){
            throw new BusinessException("资产名称,RFID编号不能重复");
        }
        Asset asset = BeanUtil.copyProperties(assetChgVO, Asset.class);
        return assetMapper.updateById(asset);
    }

    private Asset getAssetByParam(String name, String assetNo, String rfidNo){
        QueryWrapper<Asset> wrapper = new ExcludeEmptyQueryWrapper<>();
        wrapper.eq("name", name)
                .or()
                .eq("assetNo", assetNo)
                .or()
                .eq("rfidNo", rfidNo);
        return assetMapper.selectOne(wrapper);
    }


    @Override
    public int uploadExcel(MultipartFile file) {
        //业务校验
        BusinValidatorContext validatorContext = BusinValidatorContext.getCurrentContext();
        validatorContext.setRequestDto(file);
        validateProcessor.validate(ValidateTypeConstant.ASSET_BATCH);
        return 1;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delete(Long id) {
        if(id == null) {
            throw new BusinessException("id不能为空");
        }

        //校验状态
        Asset asset = assetMapper.selectById(id);
        if(asset.getDelStatus() != SysConstant.DELSTATUS_NOT){
            throw new BusinessException("未销毁状态的才能删除");
        }

        //校验是否绑定了RFID标签
        List<RfidAsset> rfidAssetList = rfidAssetMapper.getByAssetID(id);
        if(CollUtil.isNotEmpty(rfidAssetList)){
            throw new BusinessException("该资产已经绑定了RFID标签，不能直接删除");
        }

        //校验阅读器是否检测到过
        List<Rfidrecord> rfidrecordList = rfidrecordService.getByrAssetID(id);
        if(CollUtil.isNotEmpty(rfidrecordList)){
            throw new BusinessException("此资产已经被阅读器检测到过，不能删除");
        }

        //校验是否盘点过
        List<Invetorytask> invetorytaskList = invetorytaskService.getByAssetID(id);
        if(CollUtil.isNotEmpty(invetorytaskList)){
            throw new BusinessException("此资产已经被盘点过，不能删除");
        }
        return assetMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int destroy(AssetDestoryParam assetDestoryParam) {
        List<Long> idList = Arrays.asList(assetDestoryParam.getIds().split(",")).stream().map(Long::valueOf).collect(Collectors.toList());
        //校验状态
        List<AssetRes> resList = assetMapper.getAssetListByIDs(idList);
        for(AssetRes res: resList){
            if(res.getDelStatus() == SysConstant.DELSTATUS_ED) {
                throw new BusinessException("未销毁状态的资产才能进行销毁");
            }
        }

        Map<String, Object> map = new HashMap<>();
        map.put("delStatus", SysConstant.DELSTATUS_ED);
        map.put("delReason", assetDestoryParam.getDelReason());
        assetMapper.updateBatchByParam(idList, map);

        //更新RFID资源绑定的状态
        map.put("status", SysConstant.RFID_ASSET_STATUS);
        rfidAssetMapper.updateBatchStatus(idList, map);
        return 1;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int assetReturn(AssetReturnVParam assetReturnVParam) {
        //只有出库登记状态status=0还未出库或1已出库的才允许设置归还
        Assetoutreg assetoutreg = assetoutregMapper.selectById(assetReturnVParam.getAssetID());
        String statusStr = assetoutreg.getStatus().toString();
        if(!SysConstant.OUT_STATUS_NOT_DELIVE.toString().equals(statusStr) && !SysConstant.OUT_STATUS_DELIVEED.toString().equals(statusStr)){
            throw new BusinessException("只有出库登记状态为还未出库或已出库的才允许设置归还");
        }

        assetoutreg.setStatus(SysConstant.OUT_STATUS_RETURNED);
        assetoutreg.setReturnTime(DateUtil.parse(assetReturnVParam.getReturnTime(), "yyyy-MM-dd HH:mm:ss"));
        if(StrUtil.isNotBlank(assetReturnVParam.getRemark())){
            assetoutreg.setRemark(assetReturnVParam.getRemark());
        }
        return assetoutregMapper.updateById(assetoutreg);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int setBlackList(BlackListVParam blackListVParam) {
        String blackReason = blackListVParam.getBlackReason();
        Integer setType = blackListVParam.getSetType();
        if(SysConstant.BLACK_TYPE_SET == setType && StrUtil.isBlank(blackReason)){
            throw new BusinessException("设置黑名单时,原因不能为空");
        }
        List<Long> idList = Arrays.asList(blackListVParam.getIds().split(",")).stream().map(Long::valueOf).collect(Collectors.toList());
        Map<String, Object> map = new HashMap<>();
        map.put("isBlack", SysConstant.IS_NOT_BLACK);
        if(SysConstant.BLACK_TYPE_SET == setType){
            map.put("isBlack", SysConstant.IS_BLACK);
            map.put("blackReason", blackListVParam.getBlackReason());
        }
        return assetMapper.updateBatchByParam(idList, map);
    }

    @Override
    public void exportExcel(HttpServletResponse response, AssetQueryParam assetQueryParam) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", assetQueryParam.getName());
        map.put("assetNo", assetQueryParam.getAssetNo());
        map.put("startDate", assetQueryParam.getStartDate());
        map.put("endDate", assetQueryParam.getEndDate());
        List<AssetStatusExcel> list = assetMapper.getExportListByParam(map);

        String fileName = "资产实时状态报告";
        try {
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + new String( fileName.getBytes("gb2312"), "ISO8859-1" ) + ".xls");
            ServletOutputStream out = response.getOutputStream();
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX,true);
            Sheet sheet = new Sheet(1,0, AssetStatusExcel.class);
            //设置自适应宽度
            sheet.setAutoWidth(Boolean.TRUE);
            sheet.setSheetName("资产实时状态报告");
            writer.write(list, sheet);
            writer.finish();
            out.flush();
            response.getOutputStream().close();
            out.close();
        } catch (IOException e) {
            log.error("exportExcel异常: {}", e);
        }
    }

    @Override
    public void exportCustomExcel(HttpServletResponse response, AssetQueryParam assetQueryParam) {
       /* Map<String, Object> map = new HashMap<>();
        map.put("name", assetQueryParam.getName());
        map.put("assetNo", assetQueryParam.getAssetNo());
        List<AssetStatusExcel> list = assetMapper.getExportListByParam(map);

        String fileName = "资产实时状态报告";
        try {
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + new String( fileName.getBytes("gb2312"), "ISO8859-1" ) + ".xls");
            ServletOutputStream out = response.getOutputStream();
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX,true);
            Sheet sheet = new Sheet(1,0, AssetStatusExcel.class);
            //设置自适应宽度
            sheet.setAutoWidth(Boolean.TRUE);
            sheet.setSheetName("资产实时状态报告");
            writer.write(list, sheet);
            writer.finish();
            out.flush();
            response.getOutputStream().close();
            out.close();
        } catch (IOException e) {
            log.error("exportExcel异常: {}", e);
        }*/
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void batchImport(List<Asset> assetList, int count) {
        int insertLength = assetList.size();
        int i = 0;
        List<Asset> partialList = null;
        while (insertLength > count){
            partialList = assetList.subList(i, i+count);
            assetMapper.addBatch(partialList);
            i = i + count;
            insertLength = insertLength - count;
        }
        if(insertLength > 0){
            partialList = assetList.subList(i, i+insertLength);
            assetMapper.addBatch(partialList);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int bindRFID(BindRFIDVO bindRFIDVO) {
        Map<String, Object> map = new HashMap<>();
        map.put("rfidPrintNo", bindRFIDVO.getRfidPrintNo());
        RfidMapping existRfMp = rfidMappingService.getByMap(map);
        if(existRfMp == null){
            throw new BusinessException("该打印编号没有匹配到实际编号");
        }
        if(existRfMp.getStatus() == 1){
            throw new BusinessException("该RFID标签已经被使用");
        }

        //保存绑定
        RfidAsset rfidAsset = BeanUtil.copyProperties(bindRFIDVO, RfidAsset.class);
        rfidAssetMapper.insert(rfidAsset);

        //更新资产的roomID
        List<Long> idList = new ArrayList<>();
        idList.add(bindRFIDVO.getAssetID());
        map.put("roomID", bindRFIDVO.getRoomID());
        assetMapper.updateBatchByParam(idList, map);
        return 1;
    }

}
