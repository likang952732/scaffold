package com.wwinfo.controller;


import com.wwinfo.annotation.MyLog;
import com.wwinfo.common.CommonPage;
import com.wwinfo.common.CommonResult;
import com.wwinfo.pojo.dto.*;
import com.wwinfo.pojo.query.AssetQuery;
import com.wwinfo.pojo.res.AssetRes;
import com.wwinfo.pojo.vo.AssetAddVO;
import com.wwinfo.pojo.vo.AssetChgVO;
import com.wwinfo.pojo.vo.AssetOutRegVO;
import io.swagger.annotations.*;
import org.springframework.validation.BindingResult;
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

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxx')", required = true) })
    @ApiOperation(value = "分页获取资产")
    @PostMapping("/page")
    public CommonResult<CommonPage<AssetRes>> page(AssetQuery assetQuery) {

       /* IPage page = logService.listPage(tLog, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));*/
        return null;
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxx')", required = true) })
    @ApiOperation(value = "添加资产")
    @PostMapping("/add")
    @MyLog(operate = "添加", objectType = "系统权限管理", objectName = "资产管理", descript = "添加资产: #{#admin.username}")
    public CommonResult add(AssetAddVO assetAddVO, BindingResult result){
      /*  int count=adminService.insertAdmin(admin);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();*/
        return null;
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxx')", required = true) })
    @ApiOperation(value = "资产excel导入")
    @PostMapping("/excelImport")
    @MyLog(operate = "excel导入", objectType = "资产excel导入", objectName = "资产excel导入", descript = "资产excel导入: #{#admin.username}")
    public CommonResult uploadExcel(@ApiParam(name="file",value="excel文件",required=true)
                                        @RequestParam("file") MultipartFile file){

        return null;
    }


    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxx')", required = true) })
    @ApiOperation("编辑资产")
    @PostMapping("/update")
    public CommonResult update(AssetChgVO assetChgVO, BindingResult result) {
       /* int count = adminService.updateAdmin(admin);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();*/

        return null;
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxx')", required = true) })
    @ApiOperation("删除资产")
    @PostMapping("/delete/{ID}")
    public CommonResult delete(@ApiParam(name="ID",value="资产ID",required=true)@PathVariable("ID") Long ID) {
        /*int count = adminService.deleteAdmin(id);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();*/

        return null;
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxx')", required = true) })
    @ApiOperation("销毁资产")
    @PostMapping("/destroy")
    @MyLog(operate = "修改", objectType = "销毁资产", objectName = "销毁资产", descript = "销毁资产")
    public CommonResult updatePass(AssetDestoryParam assetDestoryParam, BindingResult result) {
     /*   int count = adminService.updatePass(UmsAdminUpdatePassParam);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();*/
        return null;
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxx')", required = true) })
   // @ApiOperation(value = "资产出库")
    @PostMapping("/retrieval")
    @MyLog(operate = "出库", objectType = "资产出库", objectName = "资产出库", descript = "资产出库: #{#admin.username}")
    public CommonResult retrieval(List<AssetOutRegVO> assetOutRegVO, BindingResult result){
      /*  int count=adminService.insertAdmin(admin);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();*/
        return null;
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxx')", required = true) })
    @ApiOperation(value = "资产归还")
    @PostMapping("/assetReturn")
    @MyLog(operate = "归还", objectType = "资产归还", objectName = "资产归还", descript = "资产归还: #{#admin.username}")
    public CommonResult assetReturn(AssetReturnVParam assetReturnVParam, BindingResult result){
      /*  int count=adminService.insertAdmin(admin);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();*/
        return null;
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "资产黑名单设置(设置或取消)")
    @PostMapping("/setBlackList")
    @MyLog(operate = "设置", objectType = "资产黑名单设置", objectName = "资产黑名单设置", descript = "资产黑名单设置: #{#admin.username}")
    public CommonResult assetReturn(BlackListVParam blackListVParam, BindingResult result){
      /*  int count=adminService.insertAdmin(admin);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();*/
        return null;
    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "实时状态查询导出excel")
    @PostMapping("/status/export")
    public void excelExport(HttpServletResponse response, AssetQueryParam assetQueryParam){
        //DeductionDetailService.exportExcel(response,tbatchDtl);

    }

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "导出excel")
    @PostMapping("/custom/export")
    public void customExport(HttpServletResponse response, AssetQueryParam assetQueryParam){
        //DeductionDetailService.exportExcel(response,tbatchDtl);

    }



}
