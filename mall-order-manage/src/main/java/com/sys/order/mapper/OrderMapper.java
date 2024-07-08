package com.sys.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sys.order.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
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

    @Select("<script> " +
            "select " +
            "tbo.order_id " +
            "from td_b_order tbo " +
            "where tbo.flag = 1 " +
            "<if test='order.orderNo != null and order.orderNo != \"\"'> " +
            "   and tbo.order_no = #{order.orderNo} " +
            "</if> " +
            "order by tbo.order_id desc" +
            "</script>")
    IPage<Order> queryOrderListByPage(IPage<Order> page, Order order);

}
