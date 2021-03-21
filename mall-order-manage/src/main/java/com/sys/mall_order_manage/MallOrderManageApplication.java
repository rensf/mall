package com.sys.mall_order_manage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.sys.mall_order_manage.mapper")
public class MallOrderManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallOrderManageApplication.class, args);
    }

}
