package com.sys.user.controller;

import com.sys.common.result.Result;
import com.sys.user.entity.CartProduct;
import com.sys.user.service.ICartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 购物车 控制器
 * @author rensf
 * @date 2021/6/8
 */
@RestController
@RequestMapping("/cart")
@Api(tags = "购物车管理")
public class CartController {

    @Resource
    private ICartService cartService;

    @GetMapping("/queryCartList")
    @ApiOperation("查询购物车")
    public Result<Object> queryCartList() {
        return Result.success(cartService.queryCart());
    }

    @PostMapping("/addCart")
    @ApiOperation("添加购物车")
    public Result<Object> addCart(@RequestBody CartProduct cartProduct) {
        return Result.success(cartService.addCart(cartProduct));
    }

}
