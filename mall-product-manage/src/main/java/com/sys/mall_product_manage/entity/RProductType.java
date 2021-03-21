package com.sys.mall_product_manage.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@TableName("tr_b_product_type")
public class RProductType {

    @TableId
    private String id;

    private String productId;

    private String productTypeId;

    private Integer flag = 1;

}
