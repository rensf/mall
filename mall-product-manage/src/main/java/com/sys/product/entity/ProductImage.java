package com.sys.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author rensf
 */
@Data
@Component
@TableName("td_b_product_image")
public class ProductImage implements Serializable {

    private static final long serialVersionUID = -5012878338433038255L;

    @TableId
    private String productImageId;

    private String productId;

    private String productImage;

    private String imagePosition;

    private Integer flag = 1;

}
