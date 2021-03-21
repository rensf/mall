package com.sys.mall_product_manage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.sys.mall_product_manage.mapper")
public class MallProductManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallProductManageApplication.class, args);
    }

}
