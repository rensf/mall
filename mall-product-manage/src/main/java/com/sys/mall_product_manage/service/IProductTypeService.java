package com.sys.mall_product_manage.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sys.mall_product_manage.entity.ProductType;

import java.util.List;
import java.util.Map;

public interface IProductTypeService extends IService<ProductType> {

    List<Map> queryProductTypeList(JSONObject param);

    Integer addProductType(ProductType type);

}
