package com.sys.product;

import org.mybatis.spring.annotation.MapperScan;
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
public class MallProductManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallProductManageApplication.class, args);
    }

}
