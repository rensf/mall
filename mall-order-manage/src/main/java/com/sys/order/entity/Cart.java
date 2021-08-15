package com.sys.order.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sys.product.entity.Product;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * @author rensf
 * @date 2021/6/8 17:21
 */
@Data
@Component
@TableName("td_b_cart")
public class Cart implements Serializable {

    private static final long serialVersionUID = 8191680091595709705L;

    /**
     * ID
     */
    private String cartId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 生效标志
     */
    private Integer flag = 1;

    /**
     * 购物车产品列表
     */
    @TableField(exist = false)
    private List<Product> productList;

}
