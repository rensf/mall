package com.sys.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sys.common.util.IDUtils;
import com.sys.order.entity.Cart;
import com.sys.order.entity.CartProduct;
import com.sys.order.mapper.CartProductMapper;
import com.sys.order.service.ICartService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author rensf
 * @date 2021/6/8 20:20
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CartServiceImpl extends ServiceImpl<CartProductMapper, CartProduct> implements ICartService {

    @Resource
    private CartProductMapper cartMapper;
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public Cart queryCart(String cartId) {
        Cart cart = new Cart();
        // 判断购物车ID是否为空
        if (cartId == null || cartId.isEmpty()) {
            // 如果为空，返回空的购物车
            cartId = IDUtils.generateID();
            cart.setCartId(cartId);
        } else {
            // 如果不为空，查询出购物车内商品
            List<CartProduct> products = cartMapper.queryProductByCartId(cartId);
            cart.setCartId(cartId);
            cart.setProductList(products);
        }
        return cart;
    }

    @Override
    public Integer addCart(CartProduct cartProduct) {
        redisTemplate.opsForValue().get("");
        return null;
    }

}
