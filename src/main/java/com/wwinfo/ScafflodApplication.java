package com.wwinfo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi
@EnableScheduling
@EnableTransactionManagement
@MapperScan({"com.wwinfo.mapper"})
@Import(cn.hutool.extra.spring.SpringUtil.class)
public class ScafflodApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScafflodApplication.class, args);
    }

}
