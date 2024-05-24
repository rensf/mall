package com.sys.product.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sys.common.core.result.Result;
import com.sys.product.entity.Product;
import com.sys.product.entity.ProductAttr;
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
    public Result<Product> queryProductById(@RequestParam String productId) {
        return Result.success(productService.queryProductById(productId));
    }

    @PostMapping("/addProduct")
    @ApiOperation("添加产品")
    public Result<Boolean> addProduct(@RequestBody Product product) {
        return Result.success(productService.addProduct(product));
    }

    /**
     * 删除产品
     * @param productId 产品ID
     * @return 是否删除成功
     */
    @DeleteMapping("/delProduct/{productId}")
    public Result<Boolean> delProduct(@PathVariable String productId) {
        return Result.success(productService.delProduct(productId));
    }

    @PutMapping("/updateProduct")
    @ApiOperation("更新产品信息")
    public Result<Boolean> updateProduct(@RequestBody Product product) {
        return Result.success(productService.updateProduct(product));
    }

    @GetMapping("/viewProductImage/{imageName}")
    @ApiOperation("预览产品图片")
    public void viewProductImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
        productService.viewProductImage(imageName, response);
    }

    @PostMapping("/uploadProductImage")
    @ApiOperation("上传产品图片")
    public Result<String> uploadProductImage(@RequestBody MultipartFile file) throws IOException {
        return Result.success(productService.uploadProductImage(file));
    }

    @DeleteMapping("/deleteProductImage/{imageName}")
    @ApiOperation("删除产品图片")
    public Result<Integer> deleteProductImage(@PathVariable("imageName") String imageName) {
        return Result.success(productService.deleteProductImage(imageName));
    }

}
