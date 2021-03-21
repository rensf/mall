package com.sys.mall_product_manage.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@TableName("td_b_product_image")
public class ProductImage {

    @TableId
    private String productImageId;

    private String productId;

    private String productImage;

    private String imagePosition;

    private Integer flag = 1;

}
