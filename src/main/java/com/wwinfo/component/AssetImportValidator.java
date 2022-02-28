package com.wwinfo.component;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.Sheet;
import com.wwinfo.annotation.BusinValidator;
import com.wwinfo.common.BusinValidatorContext;
import com.wwinfo.common.exception.BusinValidateException;
import com.wwinfo.constant.ValidateTypeConstant;
import com.wwinfo.mapper.AssetMapper;
import com.wwinfo.model.Asset;
import com.wwinfo.pojo.bo.AssetImport;
import com.wwinfo.service.AssetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 资产导入校验
 *
 * @Author: lk
 * DateTime: 2022-02-24 11:05
 */
@Component
@Slf4j
@BusinValidator(validateTypes = ValidateTypeConstant.ASSET_BATCH, validateOrder = 1)
public class AssetImportValidator  extends BusinValidatorTemplate {

    @Autowired
    private ExcelListener excelListener;

    @Autowired
    private AssetService assetService;

    @Resource
    private AssetMapper assetMapper;

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
            ExcelReader excelReader = new ExcelReader(multipartFile.getInputStream(), null, excelListener);
            //读取信息
            excelReader.read(new Sheet(1, 1, AssetImport.class));
            //获取数据
            List<Object> list = excelListener.getDatas();
            if (CollUtil.isEmpty(list)) {
                throw new BusinValidateException("未读取到数据");
            }

            if(list.size() > 1000){
                excelListener.getDatas().clear();
                throw new BusinValidateException("明细条数不能超过1000条");
            }

            List<Asset> assetList = new ArrayList<>();
            AssetImport assetImport = null;
            Asset asset = null;
            for (int i = 0; i < list.size(); i++) {
                assetImport = (AssetImport)list.get(i);
                asset = BeanUtil.copyProperties(assetImport, Asset.class);
                assetList.add(asset);
            }

            List<Asset> existList = assetMapper.checkRepeat(assetList);
            if(CollUtil.isNotEmpty(existList)){
                throw new BusinValidateException("资产名称,资产编号,RFID编号不能重复");
            }

            assetService.batchImport(assetList, 100);
        } catch (IOException e) {
            log.error("AssetImportValidator校验异常: {}", e);
        } finally {
            excelListener.getDatas().clear();
        }
    }
}


