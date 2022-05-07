package com.wwinfo.component;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.Sheet;
import com.wwinfo.annotation.BusinValidator;
import com.wwinfo.common.BusinValidatorContext;
import com.wwinfo.common.exception.BusinValidateException;
import com.wwinfo.constant.ValidateTypeConstant;
import com.wwinfo.mapper.AssetMapper;
import com.wwinfo.mapper.RfidMappingMapper;
import com.wwinfo.model.Asset;
import com.wwinfo.model.RfidAsset;
import com.wwinfo.model.RfidMapping;
import com.wwinfo.pojo.bo.AssetBatchBind;
import com.wwinfo.service.RfidAssetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * Description: 资产批量绑定校验
 *
 * @Author: lk
 */
@Component
@Slf4j
@BusinValidator(validateTypes = ValidateTypeConstant.ASSET_BATCH_BIND, validateOrder = 1)
public class AssetBatchBindValidator extends BusinValidatorTemplate {

    @Autowired
    private ExcelListener excelListener;

    @Resource
    private AssetMapper assetMapper;

    @Resource
    private RfidMappingMapper rfidMappingMapper;

    @Resource
    private RfidAssetService rfidAssetService;

    @Override
    public void validateInner() throws BusinValidateException {
        BusinValidatorContext context = BusinValidatorContext.getCurrentContext();
        MultipartFile multipartFile = context.getRequestDto();
        if(multipartFile == null){
            throw new BusinValidateException("未获取到上传文件");
        }
        String fileName = multipartFile.getOriginalFilename().toLowerCase();
        if(!(fileName.endsWith(".xlsx") || fileName.endsWith(".xls"))){
            throw new BusinValidateException("文件格式错误");
        }

        try {
            ExcelReader excelReader = new ExcelReader(new BufferedInputStream(multipartFile.getInputStream()), null, excelListener);
            //读取信息
            excelReader.read(new Sheet(1, 1, AssetBatchBind.class));
            //获取数据
            List<Object> list = excelListener.getDatas();
            if (CollUtil.isEmpty(list)) {
                throw new BusinValidateException("未读取到数据");
            }

            if(list.size() > 1000){
                excelListener.getDatas().clear();
                throw new BusinValidateException("明细条数不能超过1000条");
            }

            List<RfidAsset> bindList = new ArrayList<>();
            AssetBatchBind vo = null;
            RfidAsset rfidAsset = null;
            List<String> printList = new ArrayList<>();
            List<String> assetNoList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                vo = (AssetBatchBind)list.get(i);
                rfidAsset = BeanUtil.copyProperties(vo, RfidAsset.class);
                checkParam(vo);
                bindList.add(rfidAsset);
                assetNoList.add(vo.getAssetNo());
                printList.add(vo.getRfidPrintNo());
            }

            //校验资产编号是否存在
            List<String> filterAssetNoList = assetNoList.stream().distinct().collect(Collectors.toList());
            List<Asset> assets = assetMapper.checkAssetNoRepeat(filterAssetNoList);
            List<String> existAssetnoList = assets.stream().map(Asset::getAssetNo).collect(Collectors.toList());
            if(CollUtil.isEmpty(assets)){
                throw new BusinValidateException("资产编号全部不存在");
            }
            for(String assetno: filterAssetNoList){
                if(!existAssetnoList.contains(assetno)){
                    throw new BusinValidateException("资产编号：" +  assetno  +"不存在");
                }
            }

            //校验打印编号是否存在映射
            List<RfidMapping> printNodbList = rfidMappingMapper.getByPrintNos(printList);
            List<String> existprintList = printNodbList.stream().map(RfidMapping::getRfidPrintNo).collect(Collectors.toList());
            for(String printno: printList){
                if(!existprintList.contains(printno)){
                    throw new BusinValidateException("打印编号：" +  printno  +"未查询到映射编号");
                }

                if(printNodbList.stream().anyMatch(e -> e.getRfidPrintNo().equals(printno) && e.getStatus() == 1)){
                    throw new BusinValidateException("打印编号：" +  printno  +"已被绑定");
                }
            }
            rfidAssetService.addBatch(bindList, 200);
        } catch (IOException e) {
            log.error("AssetImportValidator校验异常: {}", e);
        } finally {
            excelListener.getDatas().clear();
        }
    }

    private void checkParam(AssetBatchBind vo) {
        if(StrUtil.isBlank(vo.getAssetNo())){
            throw new BusinValidateException("资产编号不能为空");
        }
        if(StrUtil.isBlank(vo.getRfidPrintNo())){
            throw new BusinValidateException("rfid打印编号不能为空");
        }
    }

}


