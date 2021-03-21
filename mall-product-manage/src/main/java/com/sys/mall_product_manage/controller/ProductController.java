package com.sys.mall_product_manage.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sys.mall_product_manage.common.Result;
import com.sys.mall_product_manage.entity.Product;
import com.sys.mall_product_manage.entity.ProductType;
import com.sys.mall_product_manage.service.IProductService;
import com.sys.mall_product_manage.service.IProductTypeService;
import com.sys.mall_product_manage.util.ToJO;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/product")
@Api("产品管理")
public class ProductController {

    @Resource
    private IProductService productService;
    @Resource
    private IProductTypeService productTypeService;

    @GetMapping("/queryProductList")
    public Result queryProductList(@RequestParam Map param) {
        JSONObject jsonParam = ToJO.toJO(param);
        Page page = new Page();
        page.setCurrent(jsonParam.getLong("current"));
        page.setTotal(jsonParam.getLong("total"));
        page.setSize(jsonParam.getLong("size"));
        Result result = new Result();
        result.setResult(productService.queryProductListByPage(page));
        return result;
    }

    @PostMapping("/addProduct")
    public Result addProduct(@RequestBody Product product) {
        Result result = new Result();
        result.setResult(productService.addProduct(product));
        return result;
    }

    @PutMapping("/updateProduct")
    public Result updateProduct(@RequestBody Product product) {
        Result result = new Result();
        result.setResult(productService.updateProduct(product));
        return result;
    }

    @GetMapping("/queryProductTypeList")
    public Result queryProductTypeList(@RequestParam Map param) {
        Result result = new Result();
        result.setResult(productTypeService.queryProductTypeList(ToJO.toJO(param)));
        return result;
    }

    @PostMapping("/addProductType")
    public Result addProductType(@RequestBody ProductType type) {
        Result result = new Result();
        result.setResult(productTypeService.addProductType(type));
        return result;
    }

    @GetMapping("/viewProductImage/{imageName}")
    public void viewProductImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
        productService.viewProductImage(imageName, response);
    }

    @PostMapping("/uploadProductImage")
    public Result uploadProductImage(@RequestBody MultipartFile file) throws IOException {
        Result result = new Result();
        result.setResult(productService.uploadProductImage(file));
        return result;
    }
}
