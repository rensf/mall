package com.sys.order.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sys.common.core.result.Result;
import com.sys.order.entity.Order;
import com.sys.order.service.IOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单 控制层
 *
 * @author rensf
 * @date 2024/1/23
 */
@RestController
@RequestMapping("/order")
@Api(tags = "订单管理")
public class OrderController {

    @Resource
    private IOrderService orderService;

    /**
     * 分页查询订单列表
     *
     * @param page 分页参数
     * @param order 订单参数
     * @return 订单分页列表
     */
    @GetMapping("/queryOrderList")
    @ApiOperation("查询订单")
    public Result<IPage<Order>> queryOrderList(Page<Order> page, Order order) {
        return Result.success(orderService.queryOrderListByPage(page, order));
    }

    @GetMapping("/addOrder")
    @ApiOperation("添加订单")
    public Result<Order> addOrder() {
        return Result.success(orderService.addOrder());
    }

    @PostMapping("/confirmOrder")
    @ApiOperation("确认订单")
    public Result<Boolean> confirmOrder(@RequestBody Order order) {
        return Result.success(orderService.confirmOrder(order));
    }

    @PostMapping("/grabOrder")
    @ApiOperation("抢购订单")
    public Result grabOrder() {
        return null;
    }

}
