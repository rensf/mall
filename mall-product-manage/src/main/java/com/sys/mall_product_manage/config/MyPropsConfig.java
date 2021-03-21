package com.sys.mall_product_manage.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "props")
public class MyPropsConfig {

    private String filepath;

}
