package com.wwinfo.util;

import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import com.wwinfo.mapper.TConfigMapper;
import com.wwinfo.model.TConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-02-24 22:02
 */
@Component
public class BusinUtil {

    @Value("${mail.host}")
    private String host;

    @Value("${mail.port}")
    private String port;

    @Value("${mail.from}")
    private String from;

    @Value("${mail.tos}")
    private String tos;

    @Value("${mail.pass}")
    private String pass;

    @Autowired
    private CheckSecritUtil checkSecritUtil;

    @Resource
    private TConfigMapper configMapper;

    public String checkRequest(HttpServletRequest request, Map<String, Object> requestParam){
        String checkMessage = "校验失败";
        try {
            checkMessage = checkSecritUtil.checkSecrit(request, requestParam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return checkMessage;
    }

    public String sendMail(String subject, String content) {
        MailAccount mailAccount = new MailAccount();
        TConfig mailHost = configMapper.getConfigByfieldName("mailHost");
        TConfig mailPost = configMapper.getConfigByfieldName("mailPost");
        TConfig mailFrom = configMapper.getConfigByfieldName("mailFrom");
        TConfig mailTos = configMapper.getConfigByfieldName("mailTos");
        TConfig mailPass = configMapper.getConfigByfieldName("mailPass");

        Optional.ofNullable(mailPost).ifPresent(e -> host = mailHost.getValue());
        Optional.ofNullable(mailPost).ifPresent(e -> port = mailPost.getValue());
        Optional.ofNullable(mailFrom).ifPresent(e -> from = mailFrom.getValue());
        Optional.ofNullable(mailTos).ifPresent(e ->  tos = mailTos.getValue());
        Optional.ofNullable(mailPass).ifPresent(e -> pass = mailPass.getValue());

        mailAccount.setHost(host);
        mailAccount.setPort(Integer.valueOf(port));
        mailAccount.setFrom(from);
        mailAccount.setPass(pass);
        return MailUtil.send(mailAccount, tos, subject, content.toString(), false);
    }
}
