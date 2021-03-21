package com.sys.mall_product_manage.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@TableName("td_b_product_type")
public class ProductType {

    @TableId
    private String productTypeId; //ID

    private String typeName; //名称

    private String typeDetail; //详述

    private String typeLevel; //等级

    private String typeParent; //父级

    private Integer flag = 1; //生效标志

}
