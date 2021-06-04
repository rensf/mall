package com.sys.order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sys.order.entity.Order;

import java.util.Map;

/**
 * @author rensf
 * @date 2021/5/8 17:05
 */
public interface IOrderService extends IService<Order> {

    /**
     * 分页查询订单列表
     * @param param
     * @return
     */
    IPage<Order> queryOrderListByPage(Map param);

    /**
     * 添加订单
     * @param order
     * @return
     */
    Integer addOrder(Order order);
}
