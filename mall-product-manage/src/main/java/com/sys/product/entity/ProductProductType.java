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
@TableName("tr_b_product_product_type")
public class ProductProductType implements Serializable {

    private static final long serialVersionUID = -5572948018211951593L;

    /**
     * ID
     */
    @TableId
    private String productProductTypeId;

    /**
     * 产品ID
     */
    private String productId;

    /**
     * 产品类别ID
     */
    private String productTypeId;

    /**
     * 生效标志
     */
    private Integer flag = 1;

}
