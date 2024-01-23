package com.sys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author rensf
 * @date 2021/5/10
 */
@SpringBootApplication
@EnableEurekaClient
@EnableOpenApi
public class MallUserManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallUserManageApplication.class, args);
    }

}
