package com.sys.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sys.user.entity.CartProduct;

import java.util.List;

/**
 * 购物车 服务接口
 *
 * @author rensf
 * @date 2021/6/8
 */
public interface ICartService extends IService<CartProduct> {

    /**
     * 查询购物车
     * @return
     */
    List<CartProduct> queryCart();

    /**
     * 产品加入购物车
     * @param cartProduct
     * @return
     */
    Integer addCart(CartProduct cartProduct);

}
