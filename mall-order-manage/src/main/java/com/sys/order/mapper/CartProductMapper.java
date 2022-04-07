package com.sys.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sys.order.entity.CartProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author rensf
 * @date 2021/6/8 20:20
 */
@Mapper
@Repository
public interface CartProductMapper extends BaseMapper<CartProduct> {

    /**
     * 通过购物车Id查询其下产品列表
     *
     * @param cartId
     * @return
     */
    @Select("SELECT \n" +
            "  a.cart_id \n" +
            "  b.product_id \n" +
            "  b.product_name \n" +
            "  b.product_model \n" +
            "  b.product_unit \n" +
            "  b.product_price \n" +
            "  b.product_discount_price \n" +
            "FROM\n" +
            "  tr_b_cart_product a \n" +
            "  LEFT JOIN td_b_product b \n" +
            "    ON a.`product_id` = b.`product_id` \n" +
            "WHERE a.`flag` = 1 \n" +
            "  AND b.`flag` = 1 \n" +
            "  AND a.`cart_id` = #{cartId} ")
    List<CartProduct> queryProductByCartId(@Param("cartId") String cartId);

}
