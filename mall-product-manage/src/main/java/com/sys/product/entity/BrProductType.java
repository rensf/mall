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
@TableName("tr_b_product_type")
public class BrProductType implements Serializable {

    private static final long serialVersionUID = -5572948018211951593L;

    @TableId
    private String id;

    private String productId;

    private String productTypeId;

    private Integer flag = 1;

}
