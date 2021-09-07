package com.example.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.common.CommonResult;
import com.example.common.exception.BusinessException;
import com.example.dto.WxLoginParam;
import com.example.service.TUserService;
import com.example.util.AESUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/*
 @Description 对接微信
 *@author kang.li
 *@date 2021/3/29 15:34   
 */
@RestController
@RequestMapping("/wx")
@Slf4j
@Api(value = "集成微信接口", tags = "微信接口")
public class WxController {
    @Value("${wechat.appid}")
    private String appid;
    @Value("${wechat.secret}")
    private String secret;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Value("${wechat.login.url}")
    private String loginUrl;

    @Autowired
    private TUserService userService;

    @ApiOperation("获取手机号授权登录")
    @PostMapping("/getPhone")
    public CommonResult miniLogin(@RequestBody WxLoginParam wxLoginParam){
        // 1.解析加密数据
        String phoneNumber = null;
        JSONObject userencryptedData = AESUtil.wxDecrypt(wxLoginParam.getEncryptedData(), wxLoginParam.getSessionKey(), wxLoginParam.getIv());
        log.info("json {}", userencryptedData.toString());
        if(userencryptedData.get("phoneNumber") == null) {
            throw new BusinessException("未获取到手机号");
        }
        phoneNumber = userencryptedData.getStr("phoneNumber");
        wxLoginParam.setPhone(phoneNumber);
        String token = userService.wxLogin(wxLoginParam);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        tokenMap.put("openId", wxLoginParam.getOpenId());
        tokenMap.put("sessionKey", wxLoginParam.getSessionKey());
        tokenMap.put("phone", phoneNumber);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation("获取openid等信息")
    @GetMapping("/getOpenId")
    public CommonResult getOpenId(WxLoginParam wxLoginParam) {
        String code = wxLoginParam.getCode();
       if(StrUtil.isBlank(code)){
           throw new BusinessException("code凭证号不能为空");
       }
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("appid", appid);
        paraMap.put("secret", secret);
        paraMap.put("js_code", code);
        paraMap.put("grant_type","authorization_code");
        log.info("微信登录接口请求参数: {}", paraMap.toString());

        String wxResult = HttpUtil.get(loginUrl, paraMap);
        log.info("微信登录接口响应: {}", wxResult);
        JSONObject json = JSONUtil.parseObj(wxResult);
        if("40029".equals(json.getStr("errcode"))) {
            return CommonResult.failed("无效的code");
        }
        wxLoginParam.setOpenId(json.getStr("openid"));
        wxLoginParam.setSessionKey(json.getStr("session_key"));

        Map<String, String> openMap = new HashMap<>();
        openMap.put("openId", wxLoginParam.getOpenId());
        openMap.put("sessionKey", wxLoginParam.getSessionKey());
        return CommonResult.success(openMap);
    }

   /* @ApiOperation("集成微信登录")
    @PostMapping("/login")
    public CommonResult wxLogin(@Validated @RequestBody WxLoginParam wxLoginParam, BindingResult result) {
        List<FieldError> fieldErrors = result.getFieldErrors();
        if(!fieldErrors.isEmpty()){
            return CommonResult.failed(fieldErrors.get(0).getDefaultMessage());
        }

        Map<String, Object> paraMap = new HashMap<>();
        String code = wxLoginParam.getCode();
        String phone = wxLoginParam.getPhone();
        log.info("微信生成的code: {}, phone: {}", code, phone);
        *//**
         * 登录凭证校验。通过 wx.login 接口获得临时登录凭证 code 后传到开发者服务器调用此接口完成登录流程。
         * GET https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
         * appid，secret 微信小程序 账户设置中存在 不能传递到前端
         *
         *//*
        paraMap.put("appid", appid);
        paraMap.put("secret", secret);
        paraMap.put("js_code", code);
        paraMap.put("grant_type","authorization_code");
        log.info("微信登录接口请求参数: {}", paraMap.toString());

        String wxResult = HttpUtil.get(loginUrl, paraMap);
        log.info("微信登录接口响应: {}", wxResult);
        JSONObject json = JSONUtil.parseObj(wxResult);
        if("40029".equals(json.getStr("errcode"))) {
            return CommonResult.failed("无效的code");
        }
        wxLoginParam.setOpenid(json.getStr("openid"));
        wxLoginParam.setSessionKey(json.getStr("session_key"));

        String token = userService.wxLogin(wxLoginParam);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        tokenMap.put("openId", wxLoginParam.getOpenid());
        tokenMap.put("sessionKey", wxLoginParam.getSessionKey());
        return CommonResult.success(tokenMap);
    }*/
}
