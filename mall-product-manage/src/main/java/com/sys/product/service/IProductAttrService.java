package com.sys.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sys.product.entity.ProductAttr;

import java.util.List;

/**
 * 产品属性 服务接口
 *
 * @author rensf
 * @date 2024/2/23
 */
public interface IProductAttrService extends IService<ProductAttr> {

    /**
     * 添加产品属性
     * @param productAttrList 产品属性列表
     * @return 结果
     */
    Boolean addProductAttr(List<ProductAttr> productAttrList);

    /**
     * 查询产品属性
     * @param productId 产品ID
     * @return 产品属性列表
     */
    List<ProductAttr> queryProductAttrs(String productId);

}
