package com.wwinfo.config;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.wwinfo.component.SwaggerProperties;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootVersion;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.Contact;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/*
 @Description Swagger3配置类
 *@author kang.li
 *@date 2021/2/19 9:39
 */
@EnableKnife4j
@Configuration
public class Swagger3Config {

    @Autowired
    private SwaggerProperties swaggerProperties;

    @Bean
    public Docket createRestApi(Environment environment) {

        //设置只在开发中环境中启动swagger
        Profiles profiles = Profiles.of("dev");
        //表示如果现在是dev环境，则返回true 开启swagger
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.OAS_30)
                .enable(swaggerProperties.getEnable())
                .apiInfo(apiInfo())
                .enable(flag)
                .host(swaggerProperties.getTryHost())//接口调试地址
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(swaggerProperties.getApplicationName() + " Restful APIs")//网页标题
                .description(swaggerProperties.getApplicationDescription())//网页描述
                .contact(new Contact("lk", "作者地址", "******qq.com"))
                .version("Application Version: " + swaggerProperties.getApplicationVersion() + ", Spring Boot Version: " + SpringBootVersion.getVersion())//接口版本号
                .termsOfServiceUrl("http://localhost:8080")
                .build();
    }

}
