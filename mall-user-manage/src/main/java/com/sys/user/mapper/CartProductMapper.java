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
     * @param userId 用户ID
     * @return 购物车产品列表
     */
    @Select("select " +
            "a.cart_product_id, " +
            "a.user_id, " +
            "a.nums, " +
            "b.product_id, " +
            "b.product_name, " +
            "b.product_model, " +
            "b.product_unit, " +
            "b.product_price, " +
            "b.product_discount_price, " +
            "c.product_image " +
            "from " +
            "tr_b_cart_product a " +
            "left join td_b_product b " +
            "on b.flag = 1 and a.product_id = b.product_id " +
            "left join td_b_product_image c " +
            "on c.flag = 1 and c.image_position = 'home' and b.product_id = c.product_id " +
            "where a.flag = 1 " +
            "and a.user_id = #{userId} ")
    List<CartProduct> queryCartListByUserId(@Param("userId") String userId);

}
