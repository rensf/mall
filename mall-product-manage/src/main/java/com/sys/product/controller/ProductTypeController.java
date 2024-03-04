package com.sys.product.controller;

import com.sys.common.core.util.JsonUtils;
import com.sys.common.core.result.Result;
import com.sys.product.entity.ProductType;
import com.sys.product.service.IProductTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 产品类型 控制层
 *
 * @author rensf
 * @date 2022/4/28
 */
@RestController
@RequestMapping("/productType")
@Api(tags = "产品类型管理")
public class ProductTypeController {

    @Resource
    private IProductTypeService productTypeService;

    @GetMapping("/queryProductTypeList")
    @ApiOperation("查询产品类型列表")
    public Result<List<ProductType>> queryProductTypeList(@RequestParam Map<String, Object> param) {
        Result<List<ProductType>> result = new Result<>();
        result.setResult(productTypeService.queryProductTypeList(JsonUtils.mapToJson(param)));
        return result;
    }

    @PostMapping("/addProductType")
    @ApiOperation("添加产品类型")
    public Result<Integer> addProductType(@RequestBody ProductType type) {
        Result<Integer> result = new Result<>();
        result.setResult(productTypeService.addProductType(type));
        return result;
    }

    @PutMapping("/updateProductType")
    @ApiOperation("更新产品类型")
    public Result<Integer> updateProductType(@RequestBody ProductType type) {
        Result<Integer> result = new Result<>();
        result.setResult(productTypeService.updateProductType(type));
        return result;
    }

    @DeleteMapping("/deleteProductType")
    @ApiOperation("删除产品类型")
    public Result<Integer> deleteProductType(@RequestBody ProductType type) {
        Result<Integer> result = new Result<>();
        result.setResult(productTypeService.deleteProductType(type));
        return result;
    }

}
