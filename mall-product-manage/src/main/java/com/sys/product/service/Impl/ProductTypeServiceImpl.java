package com.sys.product.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sys.product.entity.ProductType;
import com.sys.product.mapper.ProductTypeMapper;
import com.sys.product.service.IProductTypeService;
import com.sys.product.util.GenerateID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author rensf
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ProductTypeServiceImpl extends ServiceImpl<ProductTypeMapper, ProductType> implements IProductTypeService {

    private ProductTypeMapper typeMapper;

    @Autowired
    public ProductTypeServiceImpl(ProductTypeMapper typeMapper) {
        this.typeMapper = typeMapper;
    }

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

    @Override
    public Integer updateProductType(ProductType type) {
        return typeMapper.updateById(type);
    }

    @Override
    public Integer deleteProductType(ProductType type) {
        type.setFlag(0);
        return typeMapper.updateById(type);
    }

}
