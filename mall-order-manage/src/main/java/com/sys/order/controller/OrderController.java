package com.sys.order.controller;

import com.sys.order.common.Result;
import com.sys.order.service.IOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rensf
 */
@RestController
@RequestMapping("/order")
@Api("订单管理")
public class OrderController {

    private IOrderService orderService;

    @Autowired
    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/addOrder")
    @ApiOperation("添加订单")
    public Result addOrder() {
        return null;
    }

    @GetMapping("/queryOrder")
    @ApiOperation("查询订单")
    public Result queryOrder() {
        return null;
    }
    
}
