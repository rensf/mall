package com.sys.stock.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rensf
 * @date 2021/5/17 16:49
 */
@RestController
@RequestMapping("/stock")
@Api("库存管理")
public class StockController {

    /**
     * 测试获取配置中心配置信息
     */
    @Value("${mysql-url}")
    private String mysqlUrl;
    @GetMapping("/test")
    public String testConfig() {
        return mysqlUrl;
    }

}
