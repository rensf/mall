package com.sys.order.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 订单 实体
 *
 * @author rensf
 * @date 2021/5/8
 */
@Data
@Component
@TableName("td_b_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 5993552310513587693L;

    /**
     * ID
     */
    @TableId
    private String orderId;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 订单地址ID
     */
    private String addressId;

    /**
     * 订单时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderTime;

    /**
     * 订单状态
     */
    private Integer orderStatus;

    /**
     * 生效标志
     */
    private Integer flag = 1;

    /**
     * 订单商品列表
     */
    @TableField(exist = false)
    private List<OrderItem> orderItems;

    /**
     * 预计送达日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate esArrivalDate;

}
