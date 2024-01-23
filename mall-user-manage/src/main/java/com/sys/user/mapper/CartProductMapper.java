package com.sys.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sys.user.entity.CartProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 购物车产品 映射层
 *
 * @author rensf
 * @date 2021/6/8
 */
@Mapper
@Repository
public interface CartProductMapper extends BaseMapper<CartProduct> {

    /**
     * 通过购物车Id查询其下产品列表
     *
     * @param userId
     * @return
     */
    @Select("SELECT " +
            "a.user_id, " +
            "b.product_id, " +
            "b.product_name, " +
            "b.product_model, " +
            "b.product_unit, " +
            "b.product_price, " +
            "b.product_discount_price " +
            "FROM " +
            "tr_b_cart_product a " +
            "LEFT JOIN td_b_product b " +
            "ON a.`product_id` = b.`product_id` " +
            "WHERE a.`flag` = 1 " +
            "AND b.`flag` = 1 " +
            "AND a.`user_id` = #{userId} ")
    List<CartProduct> queryProductByCartId(@Param("userId") String userId);

}
