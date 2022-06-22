package com.wwinfo.controller;

import cn.hutool.core.util.StrUtil;
import com.wwinfo.common.CommonResult;
import com.wwinfo.service.UploadService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


/*
 @Description 上传文件
 *@author kang.li
 *@date 2021/2/19 9:19   
 */
@Api(value = "文件上传接口", tags = "文件上传接口类")
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;


    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记(传参例子: Authorization:  'Bearer 12372xxxxxx')", required = true) })
    @ApiOperation(value = "图片上传", notes = "上传图片", httpMethod="POST" ,consumes="multipart/form-data")
    @PostMapping("/image")
    public CommonResult uploadImage(@ApiParam(name="file",value="文件对象",required=true)
                                        MultipartFile file, HttpServletRequest request){
        String url = uploadService.uploadPic(file, request);
       /* if(StrUtil.hasBlank(url)) {
            return ResponseEntity.badRequest().build();
        }*/
        //return ResponseEntity.status(HttpStatus.CREATED).body(url);
        return null;
    }

}
