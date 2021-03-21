package com.sys.mall_product_manage.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@TableName("td_b_product")
public class Product {

    @TableId
    private String productId; //ID

    private String productName; //名称

    private String productModel; //型号

    private String productUnit; //单位

    private String productPrice; //价格

    private String productFPrice; //优惠价格

    private String productDetail; //详述

    private Integer productFirst = 0; //是否首推

    private Integer flag = 1; //生效标志

    @TableField(exist = false)
    private String typeName; //类型

    @TableField(exist = false)
    private String typeId; //类型ID

    @TableField(exist = false)
    private String[] typeIds; //类型ID集合

    @TableField(exist = false)
    private String image; //图片ID

    @TableField(exist = false)
    private String[] images; //图片ID集合

    @TableField(exist = false)
    private String homeImage; //首页图片ID

    @TableField(exist = false)
    private String[] homeImages; //首页图片ID集合

}
