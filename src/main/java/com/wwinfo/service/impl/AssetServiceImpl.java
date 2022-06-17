package com.wwinfo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.BetweenFormatter;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
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
import com.wwinfo.mapper.RfidMappingMapper;
import com.wwinfo.model.*;
import com.wwinfo.mapper.AssetMapper;
import com.wwinfo.pojo.bo.AssetImport;
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
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
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

    @Resource
    private RfidMappingMapper rfidMappingMapper;

    @Override
    public IPage listPage(AssetQuery assetQuery) {
        User user = UserUtil.getCurrentUser();
        Optional.ofNullable(user).ifPresent(e -> assetQuery.setCanorgID(user.getOrgID()));
        Page<AssetRes> page = new Page<>(assetQuery.getPageNum(), assetQuery.getPageSize());
        //DateUtil.formatBetween();
        IPage pageResult = assetMapper.page(page, assetQuery);
        List<AssetRes> records = pageResult.getRecords();
        if(CollUtil.isNotEmpty(records)){
            for(AssetRes res: records){
                if(res.getLendStart() != null){
                    Date endDate = res.getLendEnd() == null ? DateUtil.date() : res.getLendEnd();
                    String outDuration = DateUtil.formatBetween(res.getLendStart(), endDate, BetweenFormatter.Level.HOUR);
                    res.setOutDuration(outDuration);
                }
            }
        }
        return pageResult;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int add(AssetAddVO assetAddVO) {
        String price = assetAddVO.getPrice();
        if(StrUtil.isNotBlank(price) && !price.matches("[0-9]+(.[0-9]+)?")){
            throw new BusinessException("价格参数值不合法");
        }

        if(assetAddVO.getCurStatus() == 0 && assetAddVO.getRoomID() == null){
            throw new BusinessException("当前状态为内部，所在楼宇不能为空");
        }

        Asset existAsset = getAssetByParam(assetAddVO.getAssetNo());
        if(existAsset != null){
            throw new BusinessException("资产编号不能重复");
        }
        Asset asset = BeanUtil.copyProperties(assetAddVO, Asset.class);
        asset.setDelStatus(SysConstant.DELSTATUS_NOT);
        asset.setTimeStatus(new Date());
        return assetMapper.insert(asset);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int update(AssetChgVO assetChgVO) {
        String price = assetChgVO.getPrice();
        if(StrUtil.isNotBlank(price) && !price.matches("[0-9]+(.[0-9]+)?")){
            throw new BusinessException("价格参数值不合法");
        }

        if(assetChgVO.getCurStatus() == 0 && assetChgVO.getRoomID() == null){
            throw new BusinessException("当前状态为内部，所在楼宇不能为空");
        }

        QueryWrapper<Asset> wrapper = new ExcludeEmptyQueryWrapper<>();
        wrapper.eq("assetNo", assetChgVO.getAssetNo());
        Asset existAsset = assetMapper.selectOne(wrapper);
        if(existAsset != null && assetChgVO.getId() != existAsset.getID()){
            throw new BusinessException("资产编号不能重复");
        }
        Asset asset = BeanUtil.copyProperties(assetChgVO, Asset.class);
        asset.setID(assetChgVO.getId());
        asset.setTimeModify(new Date());
        return assetMapper.updateById(asset);
    }

    private Asset getAssetByParam(String assetNo){
        QueryWrapper<Asset> wrapper = new ExcludeEmptyQueryWrapper<>();
        wrapper.eq("assetNo", assetNo);
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
        if(asset == null){
            return 0;
        }
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
        map.put("delTime", DateUtil.date());
        assetMapper.updateBatchByParam(idList, map);

        //更新RFID资源绑定的状态
        map.put("status", SysConstant.RFID_ASSET_STATUS);
        rfidAssetMapper.updateBatchStatus(idList, map);

        List<RfidAsset> list = rfidAssetMapper.getByBatchAssetId(idList);
        List<String> printList = list.stream().map(RfidAsset::getRfidPrintNo).collect(Collectors.toList());
        rfidMappingMapper.updateBatch(printList, 2);
        return 1;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int assetReturn(AssetReturnVParam assetReturnVParam) {
        //只有出库登记状态status=0还未出库或1已出库的才允许设置归还
        Assetoutreg assetoutreg = assetoutregMapper.getByAssetID(assetReturnVParam.getAssetID());
        if(assetoutreg == null){
            throw new BusinessException("未查询到出库登记记录");
        }
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
        List<Asset> assets = assetMapper.selectBatchIds(idList);
        if(CollUtil.isNotEmpty(assets)){
            for(Asset asset: assets){
                if(asset.getDelStatus() == 1){
                    throw new BusinessException("编号为: " + asset.getAssetNo() + "的资产已被销毁");
                }
            }
        }
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
        User user = UserUtil.getCurrentUser();
        map.put("name", assetQueryParam.getName());
        map.put("assetNo", assetQueryParam.getAssetNo());
        map.put("startDate", assetQueryParam.getStartDate());
        map.put("endDate", assetQueryParam.getEndDate());
        map.put("orgID", assetQueryParam.getOrgID());
        Optional.ofNullable(user).ifPresent(e ->  map.put("canorgID", UserUtil.getCurrentUser().getOrgID()));
        Optional.ofNullable(assetQueryParam.getCurStatus()).ifPresent(e ->  map.put("curStatus", assetQueryParam.getCurStatus()));
        Optional.ofNullable(assetQueryParam.getIsAbnormal()).ifPresent(e ->  map.put("isAbnormal", assetQueryParam.getIsAbnormal()));
        Optional.ofNullable(assetQueryParam.getLendStatus()).ifPresent(e ->  map.put("lendStatus", assetQueryParam.getLendStatus()));
        Optional.ofNullable(assetQueryParam.getDelStatus()).ifPresent(e ->  map.put("delStatus", assetQueryParam.getDelStatus()));
        Optional.ofNullable(assetQueryParam.getIsBlack()).ifPresent(e ->  map.put("isBlack", assetQueryParam.getIsBlack()));
        List<AssetStatusExcel> list = assetMapper.getExportListByParam(map);
        list.stream().forEach(e -> {
            if("0".equals(e.getCurStatus())){
                e.setCurStatus("内部");
            } else if("1".equals(e.getCurStatus())){
                e.setCurStatus("外部");
            }
        });

        String fileName = "资产实时状态报告";
        try {
            WriteCellStyle headWriteCellStyle = new WriteCellStyle();
            //设置背景颜色
            headWriteCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            //内容策略
            WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
            //设置 水平居中
            contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
            HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);

            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            // 这里需要设置不关闭流
            EasyExcel.write(response.getOutputStream(), AssetStatusExcel.class)
                    .registerWriteHandler(horizontalCellStyleStrategy).sheet("sheet1")
                    .doWrite(list);


          /*  response.setContentType("application/vnd.ms-excel;charset=utf-8");
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
            out.close();*/
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
        Long assetID = bindRFIDVO.getAssetID();
        Asset asset = assetMapper.selectById(assetID);
        if(asset.getDelStatus() == 1){
            throw new BusinessException("该资产已被销毁");
        }

        List<String> rfidPrintNoList = Arrays.asList(bindRFIDVO.getRfidPrintNo().split(","));

        //校验打印编号不能重复
        Set printSet = new HashSet<>(rfidPrintNoList);
        if (rfidPrintNoList.size() != printSet.size()) {
            throw new BusinessException("打印编号不能重复");
        }

        List<RfidMapping> mappList = rfidMappingMapper.getByPrintNos(rfidPrintNoList);
        if(CollUtil.isEmpty(mappList)){
            throw new BusinessException("打印编号全部没有匹配到实际编号");
        }

        //校验是否废弃
        for(RfidMapping m : mappList) {
            if(m.getStatus() == 2){
                throw new BusinessException("打印编号" + m.getRfidPrintNo() + "已被销毁");
            }
        }

        List<String> queryPrintList = mappList.stream().map(RfidMapping::getRfidPrintNo).collect(Collectors.toList());
        List<String> diffeCollect = rfidPrintNoList.stream().filter(item -> !queryPrintList.contains(item)).collect(Collectors.toList());
        if(CollUtil.isNotEmpty(diffeCollect)){
            String diffStr = diffeCollect.stream().map(String::valueOf).collect(Collectors.joining(","));
            throw new BusinessException("打印编号" + diffStr + "没有匹配到实际编号");
        }

        List<RfidAsset> bindPrintList = rfidAssetMapper.getByAssetID(assetID);
        if(CollUtil.isEmpty(bindPrintList)){
            for(RfidMapping mapp: mappList){
                if(mapp.getStatus() == 1){
                    throw new BusinessException("打印编号: " + mapp.getRfidPrintNo()   +  "对应的RFID标签已经被使用");
                }
            }
        } else {
            List<String> existPrintList = bindPrintList.stream().map(RfidAsset::getRfidPrintNo).collect(Collectors.toList());
            if(rfidPrintNoList.size() < existPrintList.size()){  //删除了printNo
                List<String> diffeList = existPrintList.stream().filter(item -> !rfidPrintNoList.contains(item)).collect(Collectors.toList());
                rfidMappingMapper.updateBatch(diffeList, 2);   //废弃
            } else if (rfidPrintNoList.size() > existPrintList.size()){
                List<String> diffeList = rfidPrintNoList.stream().filter(item -> !existPrintList.contains(item)).collect(Collectors.toList());
                List<RfidMapping> mappList2 = rfidMappingMapper.getByPrintNos(diffeList);
                for(RfidMapping mapp: mappList2){
                    if(mapp.getStatus() == 1){
                        throw new BusinessException("打印编号: " + mapp.getRfidPrintNo()   +  "对应的RFID标签已经被使用");
                    }
                }
            }
            rfidAssetMapper.deleteByAssetId(assetID);
        }

        List<RfidAsset> list = new ArrayList<>();
        RfidAsset rfidAsset = null;
        //List<String> existPrintList = mappList.stream().map(e -> e.getRfidPrintNo()).collect(Collectors.toList());
        Map<String, String> mappingMap = mappList.stream().collect(Collectors.toMap(RfidMapping::getRfidPrintNo, RfidMapping::getRfidRealNo));
        for(String printNo: rfidPrintNoList){
           /* if(!existPrintList.contains(printNo)){
                throw new BusinessException("打印编号: " + printNo + "没有匹配到实际编号");
            }*/
            rfidAsset = new RfidAsset();
            rfidAsset.setAssetID(assetID);
            rfidAsset.setRfidPrintNo(printNo);
            rfidAsset.setRfidRealNo(mappingMap.get(printNo));
            list.add(rfidAsset);
        }

        //保存绑定
        QueryWrapper<RfidAsset> wrapper = new QueryWrapper<>();
        wrapper.eq("assetID", assetID);
        rfidAssetMapper.delete(wrapper);
        rfidAssetMapper.addBatch(list);

        Map<String, Object> map = new HashMap<>();
        map.put("assetID", assetID);
        map.put("isBand", 1);
        assetMapper.updateByParam(map);

        //更新RFID状态
        rfidMappingMapper.updateBatch(rfidPrintNoList, 1);
        return 1;
    }

    @Override
    public List<HashMap<String, Object>> getAllRFIDRecord(Map<String, Object> condition) {
        return assetMapper.getAllRFIDRecord(condition);
    }

    @Override
    public void getTemplate(HttpServletResponse response) {
        List<AssetImport> list = new ArrayList<>();
        String fileName = "资产导入模板";
        try {
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1" ) + ".xlsx");
            ServletOutputStream out = response.getOutputStream();
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX,true);
            Sheet sheet = new Sheet(1,0, AssetImport.class);
            //设置自适应宽度
            sheet.setAutoWidth(Boolean.TRUE);
            sheet.setSheetName("资产导入");
            writer.write(list,sheet);
            writer.finish();
            out.flush();
            response.getOutputStream().close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<AssetRes> listAll(AssetQuery assetQuery) {
        return assetMapper.listAll(assetQuery);
    }

    @Override
    public int excelBind(MultipartFile file) {
        //业务校验
        BusinValidatorContext validatorContext = BusinValidatorContext.getCurrentContext();
        validatorContext.setRequestDto(file);
        validateProcessor.validate(ValidateTypeConstant.ASSET_BATCH);
        return 1;
    }

    @Override
    public IPage bindPage(AssetQuery assetQuery) {
        //Optional.ofNullable(assetQuery.getOrgID()).ifPresent(e -> assetQuery.setCanorgID(assetQuery.getOrgID()));
        User user = UserUtil.getCurrentUser();
        Optional.ofNullable(user).ifPresent(e -> assetQuery.setCanorgID(user.getOrgID()));
        Page<AssetRes> page = new Page<>(assetQuery.getPageNum(), assetQuery.getPageSize());
        return assetMapper.bindPage(page, assetQuery);
    }

    public static void main(String[] args) {
        String reg = "[0-9]+(.[0-9]+)?";
        String str = "0.2";
        if(str.matches(reg)){
            System.out.println("000000000000000");
        }
    }

}
