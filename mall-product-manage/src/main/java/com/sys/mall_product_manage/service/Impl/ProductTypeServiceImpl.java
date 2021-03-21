package com.sys.mall_product_manage.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sys.mall_product_manage.entity.ProductType;
import com.sys.mall_product_manage.mapper.ProductTypeMapper;
import com.sys.mall_product_manage.service.IProductTypeService;
import com.sys.mall_product_manage.util.GenerateID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ProductTypeServiceImpl extends ServiceImpl<ProductTypeMapper, ProductType> implements IProductTypeService {

    @Resource
    private ProductTypeMapper typeMapper;

    @Override
    public List<Map> queryProductTypeList(JSONObject param) {
        QueryWrapper qw = new QueryWrapper();
        qw.eq("flag", 1);
        qw.eq(param.getString("typeLevel") != null, "type_level", param.getString("typeLevel"));
        return typeMapper.selectList(qw);
    }

    @Override
    public Integer addProductType(ProductType type) {
        type.setProductTypeId(GenerateID.generateID());
        return typeMapper.insert(type);
    }

}
