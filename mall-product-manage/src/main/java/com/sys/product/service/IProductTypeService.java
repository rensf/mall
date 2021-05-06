package com.sys.product.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sys.product.entity.ProductType;

import java.util.List;
import java.util.Map;

/**
 * 产品类型 服务接口
 * @author rensf
 */
public interface IProductTypeService extends IService<ProductType> {

    /**
     * 查询产品类型列表
     * @param param
     * @return
     */
    List<Map> queryProductTypeList(JSONObject param);

    /**
     * 添加产品类型
     * @param type
     * @return
     */
    Integer addProductType(ProductType type);

}
