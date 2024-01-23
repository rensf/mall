package com.sys.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sys.order.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 订单 映射层
 *
 * @author rensf
 * @date 2021/5/8
 */
@Mapper
@Repository
public interface OrderMapper extends BaseMapper<Order> {
}
