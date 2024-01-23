package com.sys.user.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sys.common.util.TokenUtils;
import com.sys.user.entity.CartProduct;
import com.sys.user.mapper.CartProductMapper;
import com.sys.user.mapper.UserMapper;
import com.sys.user.service.ICartService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 购物车 服务层
 *
 * @author rensf
 * @date 2021/6/8
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartProductMapper, CartProduct> implements ICartService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public List<CartProduct> queryCart() {
        String userId = TokenUtils.getJwtPayload().getString("userId");
        return this.baseMapper.selectList(new LambdaQueryWrapper<CartProduct>()
            .eq(CartProduct::getUserId, userId));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer addCart(CartProduct cartProduct) {
        // 根据用户ID和产品ID判断用户购物车中是否存在该商品
        CartProduct hasProduct = this.baseMapper.selectOne(new LambdaQueryWrapper<CartProduct>()
            .eq(CartProduct::getUserId, cartProduct.getUserId())
            .eq(CartProduct::getProductId, cartProduct.getProductId()));
        if (ObjectUtil.isEmpty(hasProduct)) {
            // 不存在，将产品加入购物车
            cartProduct.setNums(1);
            return this.baseMapper.insert(cartProduct);
        } else {
            // 存在，将产品数量加一
            hasProduct.setNums(hasProduct.getNums() + 1);
            return this.baseMapper.updateById(hasProduct);
        }
    }

}
