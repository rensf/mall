package com.sys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author rensf
 * @date 2021/6/24 15:48
 */
@SpringBootApplication
@EnableEurekaClient
public class MallCommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallCommonApplication.class, args);
    }

}
