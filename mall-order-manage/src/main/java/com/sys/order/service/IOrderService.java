package com.sys.order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sys.order.entity.Order;
import com.sys.order.entity.OrderItem;

import java.util.List;

/**
 * 订单 服务接口
 *
 * @author rensf
 * @date 2021/5/8
 */
public interface IOrderService extends IService<Order> {

    /**
     * 分页查询订单列表
     *
     * @param page 分页参数
     * @param order 订单参数
     * @return 订单分页列表
     */
    IPage<Order> queryOrderListByPage(Page<Order> page, Order order);

    /**
     * 根据订单 Id查询订单详情
     * @return
     */
    List<OrderItem> queryOrderItemListByOrderId();

    /**
     * 添加订单
     * @return 订单ID
     */
    Order addOrder();

    /**
     * 确认订单
     * @param order 订单信息
     * @return 确认结果
     */
    Boolean confirmOrder(Order order);

    /**
     * 修改订单状态
     * @param status
     * @return
     */
    Integer updateOrderStatus(Integer status);

    /**
     * 抢购订单
     * @param order
     * @return
     */
    Integer grabOrder(Order order);

}
