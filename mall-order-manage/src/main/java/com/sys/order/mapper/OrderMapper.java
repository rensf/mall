package com.sys.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sys.order.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author rensf
 * @date 2021/5/8 17:16
 */
@Mapper
@Repository
public interface OrderMapper extends BaseMapper<Order> {
}
