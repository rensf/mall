package com.sys.order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sys.order.entity.Cart;

/**
 * @author rensf
 * @date 2021/6/8 20:18
 */
public interface ICartService extends IService<Cart> {

    /**
     * 分页查询购物车列表
     * @return
     */
    IPage<Cart> queryCartListByPage();

    /**
     * 加入购物车
     * @param cart
     * @return
     */
    Integer addCart(Cart cart);

}
