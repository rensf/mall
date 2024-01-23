package com.sys.product.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sys.common.result.Result;
import com.sys.product.entity.Product;
import com.sys.product.service.IProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 产品 控制层
 *
 * @author rensf
 * @date 2021/3/26
 */
@RefreshScope
@RestController
@RequestMapping("/product")
@Api(tags = "产品管理")
public class ProductController {

    @Resource
    private IProductService productService;

    /**
     * 分页查询产品列表
     *
     * @param page 分页参数
     * @param product 产品参数
     * @return 产品分页列表
     */
    @GetMapping("/queryProductList")
    @ApiOperation("查询产品列表")
    public Result<IPage<Product>> queryProductList(Page<Product> page, Product product) {
        Result<IPage<Product>> result = new Result<>();
        result.setResult(productService.queryProductListByPage(page, product));
        return result;
    }

    /**
     * 根据产品ID查询产品信息
     * @param productId 产品ID
     * @return 产品信息
     */
    @GetMapping("/queryProductById")
    @ApiOperation("根据产品ID查询产品信息")
    public Result<Product> queryProductById(String productId) {
        Result<Product> result = new Result<>();
        result.setResult(productService.queryProductById(productId));
        return result;
    }

    @PostMapping("/addProduct")
    @ApiOperation("添加产品")
    public Result<Integer> addProduct(@RequestBody Product product) {
        Result<Integer> result = new Result<>();
        result.setResult(productService.addProduct(product));
        return result;
    }

    @PutMapping("/updateProduct")
    @ApiOperation("更新产品信息")
    public Result<Integer> updateProduct(@RequestBody Product product) {
        Result<Integer> result = new Result<>();
        result.setResult(productService.updateProduct(product));
        return result;
    }

    @GetMapping("/viewProductImage/{imageName}")
    @ApiOperation("预览产品图片")
    public void viewProductImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
        productService.viewProductImage(imageName, response);
    }

    @PostMapping("/uploadProductImage")
    @ApiOperation("上传产品图片")
    public Result uploadProductImage(@RequestBody MultipartFile file) throws IOException {
        Result result = new Result();
        result.setResult(productService.uploadProductImage(file));
        return result;
    }

    @DeleteMapping("/deleteProductImage/{imageName}")
    @ApiOperation("删除产品图片")
    public Result deleteProductImage(@PathVariable("imageName") String imageName) {
        Result result = new Result();
        result.setResult(productService.deleteProductImage(imageName));
        return result;
    }

}
