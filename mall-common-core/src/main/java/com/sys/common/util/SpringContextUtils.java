package com.sys.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author rensf
 * @date 2022/8/24
 */
public class SpringContextUtils implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtils.context = applicationContext;
    }

    public static Object getBean(String name) {
        return SpringContextUtils.context.getBean(name);
    }

}
