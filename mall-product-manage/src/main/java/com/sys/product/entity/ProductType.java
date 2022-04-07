package com.sys.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 产品类型
 * @author rensf
 * @date 2022/1/26 16:03
 */
@Data
@Component
@TableName("td_b_product_type")
public class ProductType implements Serializable {

    private static final long serialVersionUID = 3562971410681356941L;

    /**
     * ID
     */
    @TableId
    private String productTypeId;

    /**
     * 产品类型名称
     */
    private String productTypeName;

    /**
     * 产品类型描述
     */
    private String productTypeDetail;

    /**
     *产品类型等级
     */
    private Integer productTypeLevel;

    /**
     * 父级产品类型ID
     */
    private String productParentTypeId;

    /**
     * 生效标志
     */
    private Integer flag = 1;

}
