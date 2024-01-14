package com.sys.order.controller;

import com.sys.common.result.Result;
import com.sys.order.entity.Order;
import com.sys.order.service.IOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/addOrder")
    @ApiOperation("添加订单")
    public Result<String> addOrder() {
        return Result.success(orderService.addOrder());
    }

    @PostMapping("/confirmOrder")
    @ApiOperation("确认订单")
    public Result<Integer> confirmOrder(@RequestBody Order order) { return null; }

    @GetMapping("/queryOrder")
    @ApiOperation("查询订单")
    public Result queryOrder() {
        return null;
    }

    @PostMapping("/grabOrder")
    @ApiOperation("抢购订单")
    public Result grabOrder() { return null; }
    
}
