package com.wwinfo.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wwinfo.annotation.MyLog;
import com.wwinfo.common.CommonPage;
import com.wwinfo.common.CommonResult;
import com.wwinfo.pojo.dto.*;
import com.wwinfo.pojo.query.AssetQuery;
import com.wwinfo.pojo.res.AssetRes;
import com.wwinfo.pojo.vo.AssetAddVO;
import com.wwinfo.pojo.vo.AssetChgVO;
import com.wwinfo.pojo.vo.BindRFIDVO;
import com.wwinfo.service.AssetService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 资产
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
@Api(value = "资产接口", tags = "资产接口")
@RestController
@RequestMapping("/asset")
public class AssetController {

    @Autowired
    private AssetService assetService;

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxx')", required = true) })
    @ApiOperation(value = "分页获取资产")
    @PostMapping("/page")
    public CommonResult<CommonPage<AssetRes>> page(AssetQuery assetQuery) {
        IPage page = assetService.listPage(assetQuery);
        return CommonResult.success(CommonPage.restPage(page));
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxx')", required = true) })
    @ApiOperation(value = "获取所有资产")
    @PostMapping("/list")
    public CommonResult<List<AssetRes>> listAll(AssetQuery assetQuery) {
        return CommonResult.success(assetService.listAll(assetQuery));
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxx')", required = true) })
    @ApiOperation(value = "分页获取资产绑定")
    @PostMapping("/bind/page")
    public CommonResult<CommonPage<AssetRes>> bindPage(AssetQuery assetQuery) {
        IPage page = assetService.bindPage(assetQuery);
        return CommonResult.success(CommonPage.restPage(page));
    }


    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxx')", required = true) })
    @ApiOperation(value = "添加资产")
    @PostMapping("/add")
    @MyLog(operate = "添加", objectType = "资产管理", objectName = "资产管理", descript = "添加资产: #{#assetAddVO.name}")
    public CommonResult add(@Validated AssetAddVO assetAddVO, BindingResult result){
        List<FieldError> fieldErrors = result.getFieldErrors();
        if(!fieldErrors.isEmpty()){
            return CommonResult.failed(fieldErrors.get(0).getDefaultMessage());
        }
        int count = assetService.add(assetAddVO);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxx')", required = true) })
    @ApiOperation(value = "资产excel导入")
    @PostMapping("/excelImport")
    @MyLog(operate = "excel导入", objectType = "资产excel导入", objectName = "资产excel导入", descript = "资产excel导入")
    public CommonResult uploadExcel(@ApiParam(name="file",value="excel文件",required=true)
                                        @RequestParam("file") MultipartFile file){
        int count = assetService.uploadExcel(file);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation(value = "下载资产导入模板")
    @GetMapping("/getTemplate")
    public void getTemplate(HttpServletResponse response){
        assetService.getTemplate(response);
    }


    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxx')", required = true) })
    @ApiOperation("编辑资产")
    @PostMapping("/update")
    @MyLog(operate = "编辑", objectType = "资产管理", objectName = "编辑资产", descript = "编辑资产: #{#assetChgVO.name}")
    public CommonResult update(@Validated AssetChgVO assetChgVO, BindingResult result) {
        List<FieldError> fieldErrors = result.getFieldErrors();
        if(!fieldErrors.isEmpty()){
            return CommonResult.failed(fieldErrors.get(0).getDefaultMessage());
        }
        int count = assetService.update(assetChgVO);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxx')", required = true) })
    @ApiOperation("删除资产")
    @PostMapping("/delete/{ID}")
    @MyLog(operate = "删除", objectType = "资产管理", objectName = "删除资产", descript = "删除资产: #{#ID}")
    public CommonResult delete(@ApiParam(name="ID",value="资产ID",required=true)@PathVariable("ID") Long ID) {
        int count = assetService.delete(ID);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxx')", required = true) })
    @ApiOperation("销毁资产")
    @PostMapping("/destroy")
    @MyLog(operate = "修改", objectType = "销毁资产", objectName = "销毁资产", descript = "销毁资产: #{#assetDestoryParam.ids}")
    public CommonResult destroy(@Validated AssetDestoryParam assetDestoryParam, BindingResult result) {
        List<FieldError> fieldErrors = result.getFieldErrors();
        if(!fieldErrors.isEmpty()){
            return CommonResult.failed(fieldErrors.get(0).getDefaultMessage());
        }
        int count = assetService.destroy(assetDestoryParam);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

   /* @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxx')", required = true) })
   // @ApiOperation(value = "资产出库")
    @PostMapping("/retrieval")
    @MyLog(operate = "出库", objectType = "资产出库", objectName = "资产出库", descript = "资产出库: #{#admin.username}")
    public CommonResult retrieval(List<AssetOutRegVO> assetOutRegVO, BindingResult result){
      *//*  int count=adminService.insertAdmin(admin);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();*//*
        return null;
    }*/

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxx')", required = true) })
    @ApiOperation(value = "资产归还")
    @PostMapping("/assetReturn")
    @MyLog(operate = "归还", objectType = "资产归还", objectName = "资产归还", descript = "资产归还: #{#assetReturnVParam.assetID}")
    public CommonResult assetReturn(@Validated AssetReturnVParam assetReturnVParam, BindingResult result){
        List<FieldError> fieldErrors = result.getFieldErrors();
        if(!fieldErrors.isEmpty()){
            return CommonResult.failed(fieldErrors.get(0).getDefaultMessage());
        }
        int count = assetService.assetReturn(assetReturnVParam);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "资产黑名单设置(设置或取消)")
    @PostMapping("/setBlackList")
    @MyLog(operate = "设置", objectType = "资产黑名单设置", objectName = "资产黑名单设置", descript = "资产黑名单设置: #{#blackListVParam.assetID}")
    public CommonResult setBlackList(@Validated BlackListVParam blackListVParam, BindingResult result){
        List<FieldError> fieldErrors = result.getFieldErrors();
        if(!fieldErrors.isEmpty()){
            return CommonResult.failed(fieldErrors.get(0).getDefaultMessage());
        }
        int count = assetService.setBlackList(blackListVParam);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "实时状态查询导出excel")
    @PostMapping("/status/export")
    public void excelExport(HttpServletResponse response, AssetQueryParam assetQueryParam){
        assetService.exportExcel(response, assetQueryParam);
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "导出excel")
    @PostMapping("/custom/export")
    public void customExport(HttpServletResponse response, AssetQueryParam assetQueryParam){
        assetService.exportCustomExcel(response, assetQueryParam);
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "资产RFID绑定")
    @PostMapping("/bindRFID")
    public CommonResult bindRFID(@Validated BindRFIDVO bindRFIDVO, BindingResult result){
        List<FieldError> fieldErrors = result.getFieldErrors();
        if(!fieldErrors.isEmpty()){
            return CommonResult.failed(fieldErrors.get(0).getDefaultMessage());
        }
        int count = assetService.bindRFID(bindRFIDVO);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation(value = "资产RFID批量绑定(excel上传)")
    @PostMapping("/excelBind")
    public CommonResult excelBind(@RequestParam("file") MultipartFile file){
        int count = assetService.excelBind(file);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

}
