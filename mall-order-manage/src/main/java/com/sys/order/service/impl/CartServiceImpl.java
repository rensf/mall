package com.sys.order.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sys.common.utils.GenerateID;
import com.sys.order.entity.Cart;
import com.sys.order.mapper.CartMapper;
import com.sys.order.service.ICartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author rensf
 * @date 2021/6/8 20:20
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements ICartService {

    @Override
    public IPage<Cart> queryCartListByPage() {
        return null;
    }

    @Override
    public Integer addCart(Cart cart) {
        cart.setCartId(GenerateID.generateID());
        return null;
    }

}
