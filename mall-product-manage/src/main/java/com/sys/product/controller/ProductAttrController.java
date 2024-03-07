package com.sys.product.controller;

import com.sys.common.core.result.Result;
import com.sys.product.entity.ProductAttr;
import com.sys.product.service.IProductAttrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 产品属性 控制层
 *
 * @author rensf
 * @date 2024/2/23
 */
@RestController
@RequestMapping("/productAttr")
@Api(tags = "产品属性管理")
public class ProductAttrController {

    @Resource
    private IProductAttrService productAttrService;

    /**
     * 添加或删除产品属性
     * @param productAttrList 产品属性列表
     * @return 结果
     */
    @PostMapping("/addOrDelProductAttr/{productId}")
    @ApiOperation("添加或删除产品属性")
    public Result<Boolean> addOrDelProductAttr(@PathVariable String productId, @RequestBody List<ProductAttr> productAttrList) {
        return Result.success(productAttrService.addOrDelProductAttr(productId, productAttrList));
    }

    /**
     * 查询产品属性
     * @param productId 产品ID
     * @return 产品属性列表
     */
    @GetMapping("/queryProductAttrs")
    @ApiOperation("查询产品属性")
    public Result<List<ProductAttr>> queryProductAttrs(@RequestParam String productId) {
        return Result.success(productAttrService.queryProductAttrs(productId));
    }

}
