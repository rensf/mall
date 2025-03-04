package com.sys.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 购物车产品 实体
 *
 * @author rensf
 * @date 2021/8/12
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
     * 用户ID
     */
    private String userId;

    /**
     * 库存ID
     */
    private String stockId;

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

    /**
     * 产品图片
     */
    @TableField(exist = false)
    private String productImage;

}
