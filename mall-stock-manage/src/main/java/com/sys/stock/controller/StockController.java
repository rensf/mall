package com.sys.stock.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sys.common.core.result.Result;
import com.sys.stock.entity.Stock;
import com.sys.stock.service.IStockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 库存 控制层
 *
 * @author rensf
 * @date 2021/5/17
 */
@RestController
@RequestMapping("/stock")
@Api(tags = "库存管理")
public class StockController {

    @Resource
    private IStockService stockService;

    @GetMapping("/queryStockList")
    @ApiOperation("查询库存列表")
    public Result<IPage<Stock>> queryStockList(Page<Stock> page) {
        return Result.success(stockService.page(page));
    }

    /**
     * 查询规格列表
     * @param productId 产品ID
     * @return 规格列表
     */
    @GetMapping("/querySpecList")
    @ApiOperation("查询规格列表")
    public Result<List<Stock>> querySpecList(@RequestParam String productId) {
        return Result.success(stockService.querySpecList(productId));
    }

    /**
     * 保存库存
     * @param stockList 库存列表
     * @return 结果
     */
    @PostMapping("/saveStock")
    @ApiOperation("保存库存")
    public Result<Boolean> saveStock(@RequestBody List<Stock> stockList) {
        return Result.success(stockService.saveBatch(stockList));
    }

}
