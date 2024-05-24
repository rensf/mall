package com.sys.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sys.order.entity.OrderItem;
import com.sys.order.mapper.OrderItemMapper;
import com.sys.order.service.IOrderItemService;
import org.springframework.stereotype.Service;

/**
 * 订单产品 服务层
 *
 * @author rensf
 * @date 2024/1/29
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements IOrderItemService {
}
