package com.sys.stock.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sys.common.mybatis.handler.StringToListTypeHandler;
import com.sys.stock.entity.Stock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 库存 映射层
 *
 * @author rensf
 * @date 2024/5/22
 */
@Mapper
@Repository
public interface StockMapper extends BaseMapper<Stock> {


    @Select("select " +
            "group_concat(tbpa.product_attr_id) as product_attr_ids, " +
            "group_concat(tbpa.product_attr_value) as product_attr_values " +
            "from td_b_product_attr tbpa " +
            "where tbpa.flag = 1 " +
            "and tbpa.product_id = #{productId} " +
            "group by " +
            "tbpa.product_id, tbpa.product_attr_name")
    @Result(property = "productAttrIdList", column = "product_attr_ids", typeHandler = StringToListTypeHandler.class)
    @Result(property = "productSpecList", column = "product_attr_values", typeHandler = StringToListTypeHandler.class)
    List<Stock> querySpecsByProductId(String productId);

}
