package com.sys.stock.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sys.stock.entity.Stock;

import java.util.List;

/**
 * 库存 服务接口
 * @author rensf
 * @date 2024/5/22
 */
public interface IStockService extends IService<Stock> {

    /**
     * 查询规格列表
     * @param productId 产品ID
     * @return 规格列表
     */
    List<Stock> querySpecList(String productId);

}
