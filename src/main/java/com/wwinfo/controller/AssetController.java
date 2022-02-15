package com.wwinfo.controller;


import com.wwinfo.annotation.MyLog;
import com.wwinfo.common.CommonPage;
import com.wwinfo.common.CommonResult;
import com.wwinfo.pojo.dto.AssetDestoryParam;
import com.wwinfo.pojo.dto.UserChgpwdParam;
import com.wwinfo.pojo.query.AssetQuery;
import com.wwinfo.pojo.res.AssetRes;
import com.wwinfo.pojo.vo.AssetAddVO;
import com.wwinfo.pojo.vo.AssetChgVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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


    @ApiOperation(value = "分页获取资产")
    @GetMapping("/page")
    public CommonResult<CommonPage<AssetRes>> page(AssetQuery assetQuery,
                                                   @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {

       /* IPage page = logService.listPage(tLog, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));*/
        return null;
    }

    @ApiOperation(value = "添加资产")
    @PostMapping("/add")
    @MyLog(operate = "添加", objectType = "系统权限管理", objectName = "资产管理", descript = "添加资产: #{#admin.username}")
    public CommonResult insert(@RequestBody AssetAddVO assetAddVO, BindingResult result){
      /*  int count=adminService.insertAdmin(admin);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();*/
        return null;
    }

    @ApiOperation(value = "资产excel导入")
    @PostMapping("/excelImport")
    @MyLog(operate = "excel导入", objectType = "资产excel导入", objectName = "资产excel导入", descript = "资产excel导入: #{#admin.username}")
    public CommonResult uploadExcel(@ApiParam(name="file",value="excel文件",required=true)
                                        @RequestParam("file") MultipartFile file){

        return null;
    }


    @ApiOperation("编辑资产")
    @PutMapping("/update")
    public CommonResult update(@RequestBody AssetChgVO assetChgVO, BindingResult result) {
       /* int count = adminService.updateAdmin(admin);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();*/

        return null;
    }

    @ApiOperation("删除资产")
    @DeleteMapping("/{ID}")
    public CommonResult delete(@ApiParam(name="ID",value="资产ID",required=true)@PathVariable("ID") Long ID) {
        /*int count = adminService.deleteAdmin(id);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();*/

        return null;
    }

    @ApiOperation("销毁资产")
    @PostMapping("/destroy")
    @MyLog(operate = "修改", objectType = "销毁资产", objectName = "销毁资产", descript = "销毁资产")
    public CommonResult updatePass(@RequestBody AssetDestoryParam assetDestoryParam, BindingResult result) {
     /*   int count = adminService.updatePass(UmsAdminUpdatePassParam);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();*/
        return null;
    }

}