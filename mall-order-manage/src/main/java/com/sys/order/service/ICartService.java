package com.sys.order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sys.order.entity.Cart;
import com.sys.product.entity.Product;

/**
 * @author rensf
 * @date 2021/6/8 20:18
 */
public interface ICartService extends IService<Cart> {

    /**
     * 分页查询购物车列表
     * @param userId
     * @return
     */
    IPage<Cart> queryCartListByPage(String userId);

    /**
     * 产品加入购物车
     * @param product
     * @return
     */
    Integer addCart(Product product);

}
