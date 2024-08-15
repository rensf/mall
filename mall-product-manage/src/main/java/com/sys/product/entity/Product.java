package com.sys.product.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * 产品 实体
 *
 * @author rensf
 * @date 2024/1/23
 */
@Data
@Component
@TableName("td_b_product")
@Document(indexName = "product")
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
     * 折扣价格
     */
    private String productDiscountPrice;

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
     * 类型ID
     */
    @TableField(exist = false)
    private List<String> typeIdList;

    /**
     * 类型名称
     */
    @TableField(exist = false)
    private List<String> typeNameList;

    /**
     * 图片ID
     */
    @TableField(exist = false)
    private List<String> imageList;

    /**
     * 首页图片ID
     */
    @TableField(exist = false)
    private List<String> homeImageList;

    /**
     * 购买数量
     */
    @TableField(exist = false)
    private int nums;

    /**
     * 产品属性
     */
    @TableField(exist = false)
    private List<ProductAttr> productAttrList;

    /**
     * 产品类型ID
     */
    @TableField(exist = false)
    private String productTypeId;

}
