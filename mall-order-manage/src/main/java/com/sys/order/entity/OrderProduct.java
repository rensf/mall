package com.sys.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author rensf
 * @date 2021/6/8 11:33
 */
@Data
@Component
@TableName("td_b_order_product")
public class OrderProduct implements Serializable {

    private static final long serialVersionUID = -6143703231552744819L;

    /**
     * ID
     */
    private String orderProductId;

    /**
     * 订单ID
     */
    private String orderId;

    /**
     * 产品ID
     */
    private String productId;

    /**
     * 购买数量
     */
    private Integer nums;

    /**
     * 生效标志
     */
    private Integer flag = 1;

}
