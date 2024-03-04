package com.sys.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sys.order.entity.OrderProduct;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 订单产品 映射层
 *
 * @author rensf
 * @date 2024/1/29
 */
@Mapper
@Repository
public interface OrderProductMapper extends BaseMapper<OrderProduct> {
}
