package com.sys.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sys.user.entity.Cart;
import com.sys.user.entity.CartProduct;

/**
 * @author rensf
 * @date 2021/6/8 20:18
 */
public interface ICartService extends IService<CartProduct> {

    /**
     * 查询购物车
     * @param cartId
     * @return
     */
    Cart queryCart(String cartId);

    /**
     * 产品加入购物车
     * @param cartProduct
     * @return
     */
    Integer addCart(CartProduct cartProduct);

}
