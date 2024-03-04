package com.sys.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sys.common.mybatis.handler.StringToListTypeHandler;
import com.sys.product.entity.ProductAttr;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 产品属性 映射层
 *
 * @author rensf
 * @date 2024/2/22
 */
@Mapper
@Repository
public interface ProductAttrMapper extends BaseMapper<ProductAttr> {

    @Select("select " +
            "product_id, " +
            "product_attr_name, " +
            "group_concat(product_attr_value) as product_attr_values " +
            "from " +
            "td_b_product_attr tbpa " +
            "where tbpa.flag = 1 " +
            "group by " +
            "tbpa.product_id, tbpa.product_attr_name ")
    @Result(property = "productAttrValues", column = "product_attr_values", typeHandler = StringToListTypeHandler.class)
    List<ProductAttr> queryProductAttrs(String productId);

}
