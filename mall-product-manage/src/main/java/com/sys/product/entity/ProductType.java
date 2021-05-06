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
@TableName("td_b_product_type")
public class ProductType implements Serializable {

    private static final long serialVersionUID = 3132001759810561856L;

    /**
     * ID
     */
    @TableId
    private String productTypeId;

    /**
     * 名称
     */
    private String typeName;

    /**
     * 详述
     */
    private String typeDetail;

    /**
     * 等级
     */
    private String typeLevel;

    /**
     * 父级
     */
    private String typeParent;

    /**
     * 生效标志
     */
    private Integer flag = 1;

}
