package com.sys.product.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author rensf
 */
@Data
@Component
@ConfigurationProperties(prefix = "props")
public class MyPropsConfig {

    private String filepath;

}
