package com.sys.product.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("td_b_product")
public class Product implements Serializable {

    private static final long serialVersionUID = -4554137403718165015L;

    /**
     * ID
     */
    @TableId
    private String productId;

    /**
     * 名称
     */
    private String productName;

    /**
     * 型号
     */
    private String productModel;

    /**
     * 单位
     */
    private String productUnit;

    /**
     * 价格
     */
    private String productPrice;

    /**
     * 优惠价格
     */
    private String productFPrice;

    /**
     * 详述
     */
    private String productDetail;

    /**
     * 是否首推
     */
    private Integer productFirst = 0;

    /**
     * 生效标志
     */
    private Integer flag = 1;

    /**
     * 类型
     */
    @TableField(exist = false)
    private String typeName;

    /**
     * 类型ID
     */
    @TableField(exist = false)
    private String typeId;

    /**
     * 类型ID集合
     */
    @TableField(exist = false)
    private String[] typeIds;

    /**
     * 图片ID
     */
    @TableField(exist = false)
    private String image;

    /**
     * 图片ID集合
     */
    @TableField(exist = false)
    private String[] images;

    /**
     * 首页图片ID
     */
    @TableField(exist = false)
    private String homeImage;

    /**
     * 首页图片ID集合
     */
    @TableField(exist = false)
    private String[] homeImages;

}
