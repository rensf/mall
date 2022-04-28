package com.sys.order.controller;

import com.sys.common.vo.Result;
import com.sys.order.service.IOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author rensf
 */
@RestController
@RequestMapping("/order")
@Api(tags = "订单管理")
public class OrderController {

    @Resource
    private IOrderService orderService;

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

    @PostMapping("/grabOrder")
    @ApiOperation("抢购订单")
    public Result grabOrder() { return null; }
    
}
