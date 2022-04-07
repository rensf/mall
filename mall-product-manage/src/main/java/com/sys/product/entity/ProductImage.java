package com.sys.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 产品图片
 * @author rensf
 */
@Data
@Component
@TableName("td_b_product_image")
public class ProductImage implements Serializable {

    private static final long serialVersionUID = -5012878338433038255L;

    /**
     * ID
     */
    @TableId
    private String productImageId;

    /**
     * 产品ID
     */
    private String productId;

    /**
     * 产品图片
     */
    private String productImage;

    /**
     * 图片位置
     */
    private String imagePosition;

    /**
     * 生效标志
     */
    private Integer flag = 1;

}
