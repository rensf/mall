package com.sys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author rensf
 * @date 2021/5/17 15:40
 */
@SpringBootApplication
@EnableEurekaClient
@EnableOpenApi
public class MallStockManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallStockManageApplication.class, args);
    }

}
