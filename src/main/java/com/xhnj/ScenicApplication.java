package com.xhnj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi
public class ScenicApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScenicApplication.class, args);
    }

}
