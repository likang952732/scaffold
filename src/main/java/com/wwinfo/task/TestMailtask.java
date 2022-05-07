package com.wwinfo.task;

import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import cn.hutool.poi.excel.BigExcelWriter;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.processing.SupportedSourceVersion;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 测试邮件发送excel附件
 *
 * @Author: lk
 * DateTime: 2022-03-14 10:36
 */
@Slf4j
//@Component
public class TestMailtask {


    @Scheduled(cron="0 */5 * * * ?")   //1分钟
    public void test(){
        log.info("定时发送开始");
        String fileName = "test导出报告.xlsx";
        File file = new File(fileName);
        try {
            List<Person> list = new ArrayList<>();
            list.add(new Person("a", 12));
            list.add(new Person("b语文uuwyyellkkdaaaaaaaaaaaaaaaaaa", 22));
            list.add(new Person("c", 32));
            list.add(new Person("d", 42));

            ExcelWriter writer = ExcelUtil.getWriter(file, "test");
            //ExcelWriter writer = ExcelUtil.getWriter(true);
            writer.addHeaderAlias("name", "姓名");
            writer.addHeaderAlias("age", "年龄");

            writer.write(list, true);
            writer.close();

            MailAccount mailAccount = new MailAccount();
            mailAccount.setHost("smtp.qq.com");
            mailAccount.setPort(Integer.valueOf(465));
            mailAccount.setFrom("1973711398@qq.com");
            mailAccount.setPass("mgmkbfpwsrjljhhb");

            //加密
            mailAccount.setAuth(true);
            mailAccount.setSslEnable(true);
            mailAccount.setSocketFactoryClass("javax.net.ssl.SSLSocketFactory");
            mailAccount.setSocketFactoryFallback(true);

            List<String> tos = new ArrayList<>();
            tos.add("1973711398@qq.com");
            tos.add("2816062125@qq.com");

            MailUtil.send(mailAccount, tos, "测试", "", false, file);
            log.info("定时发送完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            file.delete();
        }
    }

}
