package com.sys.user.controller;

import com.sys.common.core.result.Result;
import com.sys.user.entity.CartProduct;
import com.sys.user.service.ICartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 购物车 控制层
 *
 * @author rensf
 * @date 2021/6/8
 */
@RestController
@RequestMapping("/cart")
@Api(tags = "购物车管理")
public class CartController {

    @Resource
    private ICartService cartService;

    @GetMapping("/queryCartProductList")
    @ApiOperation("查询购物车产品列表")
    public Result<List<CartProduct>> queryCartProductList() {
        return Result.success(cartService.queryCartProductList());
    }

    @PostMapping("/addCartProduct")
    @ApiOperation("添加购物车产品")
    public Result<Integer> addCartProduct(@RequestBody CartProduct cartProduct) {
        return Result.success(cartService.addCartProduct(cartProduct));
    }

    @DeleteMapping("/delCartProduct")
    @ApiOperation("删除购物车产品")
    public Result<Integer> delCartProduct(@RequestBody CartProduct cartProduct) {
        return Result.success(cartService.delCartProduct(cartProduct));
    }

}
