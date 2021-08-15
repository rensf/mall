package com.sys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author rensf
 * @date 2021/5/21 11:24
 */
@SpringBootApplication
@EnableEurekaClient
@EnableOpenApi
public class MallSystemManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallSystemManageApplication.class, args);
    }

}
