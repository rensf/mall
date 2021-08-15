package com.sys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author rensf
 */
@SpringBootApplication
@EnableEurekaServer
public class MallEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallEurekaApplication.class, args);
    }

}
