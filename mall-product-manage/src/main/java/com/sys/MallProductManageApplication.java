package com.sys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author rensf
 * @date 2024/1/23
 */
@SpringBootApplication
@EnableEurekaClient
@EnableOpenApi
public class MallProductManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallProductManageApplication.class, args);
    }

}
