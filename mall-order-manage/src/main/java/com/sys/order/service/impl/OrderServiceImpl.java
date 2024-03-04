package com.sys.order.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sys.common.core.constant.BusinessConstants;
import com.sys.common.core.enums.OrderStatusEnum;
import com.sys.common.core.script.RedisScript;
import com.sys.common.core.util.IDUtils;
import com.sys.order.entity.Order;
import com.sys.order.entity.OrderProduct;
import com.sys.order.mapper.OrderMapper;
import com.sys.order.mapper.OrderProductMapper;
import com.sys.order.service.IOrderProductService;
import com.sys.order.service.IOrderService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 订单 服务层
 *
 * @author rensf
 * @date 2021/5/8
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private IOrderProductService orderProductService;

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
    public Boolean confirmOrder(Order order) {
        // 订单重复确认校验
        Integer flag = stringRedisTemplate.execute(new DefaultRedisScript<>(RedisScript.RELEASE_LOCK, Integer.class), Collections.singletonList(BusinessConstants.ORDER_TOKEN_PREFIX + order.getOrderId()), order.getOrderId());
        Assert.isTrue(Objects.equals(flag, 0), "订单重复提交");
        // 订单验价

        // 锁定库存

        // 保存订单明细
        List<OrderProduct> orderProductList = order.getOrderProductList();
        orderProductService.saveBatch(orderProductList);
        // 保存订单
        order.setOrderStatus(OrderStatusEnum.TO_BE_PAID.getCode());
        Boolean result = this.save(order);

        // 订单超时取消

        return result;
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
