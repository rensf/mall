package com.sys.mall_order_manage.controller;

import com.sys.mall_order_manage.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @PostMapping("/addOrder")
    public Result addOrder() {
        return null;
    }

    @GetMapping("/queryOrder")
    public Result queryOrder() {
        return null;
    }
    
}
