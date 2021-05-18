package com.sys.stock.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author rensf
 * @date 2021/5/17 16:10
 */
@Data
@Component
@ConfigurationProperties(prefix = "props")
public class MyPropsConfig {

}
