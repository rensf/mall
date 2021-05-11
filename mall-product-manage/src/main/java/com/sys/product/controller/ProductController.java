package com.sys.product.controller;

import com.sys.product.common.Result;
import com.sys.product.entity.Product;
import com.sys.product.entity.ProductType;
import com.sys.product.service.IProductService;
import com.sys.product.service.IProductTypeService;
import com.sys.product.util.ToJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author rensf
 * @date 2021/3/26
 */
@RestController
@RequestMapping("/product")
@Api("产品管理")
public class ProductController {

    private IProductService productService;
    private IProductTypeService productTypeService;

    @Autowired
    public ProductController(IProductService productService, IProductTypeService productTypeService) {
        this.productService = productService;
        this.productTypeService = productTypeService;
    }

    @GetMapping("/queryProductList")
    @ApiOperation("查询产品列表")
    public Result queryProductList(@RequestParam Map param) {
        Result result = new Result();
        result.setResult(productService.queryProductListByPage(param));
        return result;
    }

    @PostMapping("/addProduct")
    @ApiOperation("添加产品")
    public Result addProduct(@RequestBody Product product) {
        Result result = new Result();
        result.setResult(productService.addProduct(product));
        return result;
    }

    @PutMapping("/updateProduct")
    @ApiOperation("更新产品信息")
    public Result updateProduct(@RequestBody Product product) {
        Result result = new Result();
        result.setResult(productService.updateProduct(product));
        return result;
    }

    @GetMapping("/queryProductTypeList")
    @ApiOperation("查询产品类型列表")
    public Result queryProductTypeList(@RequestParam Map param) {
        Result result = new Result();
        result.setResult(productTypeService.queryProductTypeList(ToJson.toJson(param)));
        return result;
    }

    @PostMapping("/addProductType")
    @ApiOperation("添加产品类型")
    public Result addProductType(@RequestBody ProductType type) {
        Result result = new Result();
        result.setResult(productTypeService.addProductType(type));
        return result;
    }

    @PutMapping("/updateProductType")
    @ApiOperation("更新产品类型")
    public Result updateProductType(@RequestBody ProductType type) {
        Result result = new Result();
        result.setResult(productTypeService.updateProductType(type));
        return result;
    }

    @DeleteMapping("/deleteProductType")
    @ApiOperation("删除产品类型")
    public Result deleteProductType(@RequestBody ProductType type) {
        Result result = new Result();
        result.setResult(productTypeService.deleteProductType(type));
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
