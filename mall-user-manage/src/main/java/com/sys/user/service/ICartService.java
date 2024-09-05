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
     * 查询购物车产品列表
     *
     * @return 购物车产品列表
     */
    List<CartProduct> queryCartProductList();

    /**
     * 添加购物车产品
     *
     * @param cartProduct 购物车产品
     * @return 结果
     */
    Integer addCartProduct(CartProduct cartProduct);

    /**
     * 删除购物车产品
     *
     * @return 结果
     */
    Integer delCartProduct(CartProduct cartProduct);

}
