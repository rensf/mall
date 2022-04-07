package com.sys.order.controller;

import com.sys.common.vo.Result;
import com.sys.order.entity.CartProduct;
import com.sys.order.service.ICartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rensf
 * @date 2021/6/8 20:24
 */
@RestController
@RequestMapping("/cart")
@Api("购物车管理")
public class CartController {

    private ICartService cartService;

    @Autowired
    public CartController(ICartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/queryCartList")
    @ApiOperation("查询购物车")
    public Result<Object> queryCartList(String cartId) {
        return Result.success(cartService.queryCart(cartId));
    }

    @PutMapping("/addCart")
    @ApiOperation("添加购物车")
    public Result<Object> addCart(CartProduct cartProduct) {
        return Result.success(cartService.addCart(cartProduct));
    }

}
