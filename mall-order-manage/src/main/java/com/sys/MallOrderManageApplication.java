package com.sys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author rensf
 */
@SpringBootApplication
@EnableEurekaClient
@EnableOpenApi
public class MallOrderManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallOrderManageApplication.class, args);
    }

}
