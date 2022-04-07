package com.sys.order.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sys.common.utils.GenerateID;
import com.sys.order.entity.Order;
import com.sys.order.entity.OrderProduct;
import com.sys.order.mapper.OrderMapper;
import com.sys.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author rensf
 * @date 2021/5/8 17:15
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    private OrderMapper orderMapper;
    private RedisTemplate redisTemplate;

    @Autowired
    public OrderServiceImpl(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Override
    public IPage<Order> queryOrderListByPage(Map param) {
        return null;
    }

    @Override
    public List<OrderProduct> queryOrderProductByOrderId() {
        return null;
    }

    @Override
    public Integer addOrder(Order order) {
        order.setOrderId(GenerateID.generateID());
        return orderMapper.insert(order);
    }

    @Override
    public Integer updateOrderStatus(Integer status) {
        return null;
    }

    @Override

    public Integer grabOrder(Order order) {
        redisTemplate.opsForValue().setIfAbsent("", "");
        return null;
    }

}
