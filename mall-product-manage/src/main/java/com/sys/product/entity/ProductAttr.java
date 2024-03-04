package com.sys.product.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.ibatis.annotations.Result;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * 产品属性 实体
 *
 * @author rensf
 * @date 2024/2/22
 */
@Data
@Component
@TableName("td_b_product_attr")
public class ProductAttr implements Serializable {

    private static final long serialVersionUID = 5070119007108662004L;
    
    /**
     * ID
     */
    @TableId
    private String productAttrId;

    /**
     * 产品ID
     */
    private String productId;

    /**
     * 产品属性名称
     */
    private String productAttrName;

    /**
     * 产品属性值
     */
    private String productAttrValue;

    /**
     * 生效标志
     */
    private Integer flag = 1;

    /**
     * 产品属性值列表
     */
    @TableField(exist = false)
    private List<String> productAttrValues;

}
