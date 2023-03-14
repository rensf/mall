package com.sys.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * 鉴权模块启动类
 * @author rensf
 * @date 2022/12/26 16:43
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableOpenApi
public class MallAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallAuthApplication.class, args);
    }

}
