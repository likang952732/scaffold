package com.example.controller;

import com.example.util.SMSTXUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UsersController
 * @Description 【】控制器
 * @Author liuqiang
 * @Date Sat Jul 20 19:05:42 CST 2019
 * @version 1.0
 **/
@Slf4j
@RestController
@RequestMapping("/common")
public class ComController {


    @GetMapping("/sms")
    public String sendMessage1(String phone){
        int intFlag = (int)(Math.random() * 1000000);
        SMSTXUtil.SendCodeAli(phone, String.valueOf(intFlag));
        return "success";
    }
}
