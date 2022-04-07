package com.sys.order.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author rensf
 * @date 2021/8/12 15:53
 */
@Data
@Component
@TableName("tr_b_cart_product")
public class CartProduct implements Serializable {

    private static final long serialVersionUID = 4005273661547944182L;

    /**
     * ID
     */
    @TableId
    private String cartProductId;

    /**
     * 购物车ID
     */
    private String cartId;

    /**
     * 产品ID
     */
    private String productId;

    /**
     * 产品数量
     */
    private Integer nums;

    /**
     * 生效标志
     */
    private Integer flag = 1;

    /**
     * 产品名称
     */
    @TableField(exist = false)
    private String productName;

    /**
     * 产品型号
     */
    @TableField(exist = false)
    private String productModel;

    /**
     * 单位量词
     */
    @TableField(exist = false)
    private String productUnit;

    /**
     * 产品价格
     */
    @TableField(exist = false)
    private String productPrice;

    /**
     * 优惠价格
     */
    @TableField(exist = false)
    private String productDiscountPrice;

}
