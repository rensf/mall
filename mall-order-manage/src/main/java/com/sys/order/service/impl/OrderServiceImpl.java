package com.sys.order.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sys.common.core.constant.BusinessConstants;
import com.sys.common.core.enums.OrderStatusEnum;
import com.sys.common.core.script.RedisScript;
import com.sys.common.core.util.IDUtils;
import com.sys.order.entity.Order;
import com.sys.order.entity.OrderItem;
import com.sys.order.mapper.OrderMapper;
import com.sys.order.service.IOrderItemService;
import com.sys.order.service.IOrderService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

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
    private IOrderItemService orderItemService;

    @Override
    public IPage<Order> queryOrderListByPage(Page<Order> page, Order order) {
        return this.baseMapper.queryOrderListByPage(page, order);
    }

    @Override
    public List<OrderItem> queryOrderItemListByOrderId() {
        return null;
    }

    @Override
    public Order addOrder() {
        // 生成订单编号，将其存入redis，防止重复提交
        String orderNo = IDUtils.generateID();
        stringRedisTemplate.opsForValue().set(BusinessConstants.ORDER_TOKEN_PREFIX + orderNo, orderNo, 5, TimeUnit.MINUTES);
        Order order = new Order();
        order.setOrderNo(orderNo);
        order.setOrderTime(LocalDateTime.now());
        order.setEsArrivalDate(LocalDate.now().plusDays(7));
        return order;
    }

    @Override
    public Boolean confirmOrder(Order order) {
        // 订单重复确认校验
        Integer flag = stringRedisTemplate.execute(new DefaultRedisScript<>(RedisScript.RELEASE_LOCK, Integer.class),
                Collections.singletonList(BusinessConstants.ORDER_TOKEN_PREFIX + order.getOrderNo()), order.getOrderNo());
        Assert.isTrue(Objects.equals(flag, 0), "订单重复提交");
        // 订单验价

        // 锁定库存

        // 保存订单明细
        List<OrderItem> orderItems = order.getOrderItems();
        orderItemService.saveBatch(orderItems);
        // 保存订单
        order.setOrderId(IDUtils.generateID());
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
