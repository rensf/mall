package com.sys.order.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sys.common.constant.BusinessConstants;
import com.sys.common.util.IDUtils;
import com.sys.order.entity.Order;
import com.sys.order.entity.OrderProduct;
import com.sys.order.mapper.OrderMapper;
import com.sys.order.service.IOrderService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author rensf
 * @date 2021/5/8 17:15
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public IPage<Order> queryOrderListByPage(Map param) {
        return null;
    }

    @Override
    public List<OrderProduct> queryOrderProductByOrderId() {
        return null;
    }

    @Override
    public String addOrder() {
        // 生成订单ID，将其存入redis，防止重复提交
        String orderId = IDUtils.generateID();
        stringRedisTemplate.opsForValue().set(BusinessConstants.ORDER_TOKEN_PREFIX + orderId, orderId);
        return orderId;
    }

    @Override
    public Integer updateOrderStatus(Integer status) {
        return null;
    }

    @Override
    public Integer grabOrder(Order order) {
        return null;
    }

}
