package com.sys.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author rensf
 */
@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
public class MallConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallConfigApplication.class, args);
    }

}
