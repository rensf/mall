package com.sys.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sys.order.entity.OrderProduct;
import com.sys.order.mapper.OrderProductMapper;
import com.sys.order.service.IOrderProductService;
import org.springframework.stereotype.Service;

/**
 * 订单产品 服务层
 *
 * @author rensf
 * @date 2024/1/29
 */
@Service
public class OrderProductServiceImpl extends ServiceImpl<OrderProductMapper, OrderProduct> implements IOrderProductService {
}
