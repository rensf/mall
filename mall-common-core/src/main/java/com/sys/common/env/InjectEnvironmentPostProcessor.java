package com.sys.common.env;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;

import java.util.Properties;

/**
 * 初始化配置
 * @author rensf
 * @date 2022/8/16 12:49
 */
public class InjectEnvironmentPostProcessor implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
//        Properties properties = new Properties();
//        properties.setProperty("spring.cloud.config.uri", "http://127.0.0.1:8812/");
//        properties.setProperty("spring.cloud.config.name", "mall-product-manage");
//        properties.setProperty("spring.cloud.config.profile", "dev");
//        properties.setProperty("spring.cloud.config.label", "dev");
//        PropertiesPropertySource propertySource = new PropertiesPropertySource("myConfig", properties);
//        environment.getPropertySources().addFirst(propertySource);
    }

}
