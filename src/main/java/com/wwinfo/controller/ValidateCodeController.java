package com.wwinfo.controller;

/*
 @Description 验证码
 *@author kang.li
 *@date 2021/2/22 11:25   
 */
import com.wwinfo.common.CommonResult;
import com.wwinfo.util.ValidateCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Api(value = "验证码接口", tags = "验证码接口")
@RestController
@RequestMapping("/validateCode")
@Slf4j
public class ValidateCodeController {
    @Autowired
    private ValidateCodeUtil validateCode;

    @ApiOperation("获取验证码图片")
    @GetMapping("/getCaptchaImg")
    public void getCaptchaImg(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        try {
            response.setContentType("image/png");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Expire", "0");
            response.setHeader("Pragma", "no-cache");
            // getRandomCodeImage方法会直接将生成的验证码图片写入response
            validateCode.getRandomCodeImage(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ApiOperation("返回base64验证码")
    @GetMapping("/getCaptchaBase64")
    public CommonResult getCaptchaBase64(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("image/png");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Expire", "0");
        response.setHeader("Pragma", "no-cache");
        return CommonResult.success(validateCode.getRandomCodeBase64(request, response));
    }


    @GetMapping("/checkCaptcha")
    public boolean getCheckCaptcha(@RequestParam("code") String code, HttpSession session) {
        try {
            //toLowerCase() 不区分大小写进行验证码校验
            String sessionCode= String.valueOf(session.getAttribute("JCCODE")).toLowerCase();
            String receivedCode = code.toLowerCase();
            log.info("session里的验证码{}, 用户的验证码{}", sessionCode, receivedCode);
            return !sessionCode.equals("") && !receivedCode.equals("") && sessionCode.equals(receivedCode);
        } catch (Exception e) {
            return false;
        }
    }

}
