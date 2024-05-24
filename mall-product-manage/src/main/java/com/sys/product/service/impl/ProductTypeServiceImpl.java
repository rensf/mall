package com.sys.product.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sys.common.core.util.IDUtils;
import com.sys.product.entity.ProductType;
import com.sys.product.mapper.ProductTypeMapper;
import com.sys.product.service.IProductTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 产品类型 服务层
 *
 * @author rensf
 * @date 2024/1/23
 */
@Service
public class ProductTypeServiceImpl extends ServiceImpl<ProductTypeMapper, ProductType> implements IProductTypeService {

    @Resource
    private ProductTypeMapper typeMapper;

    @Override
    public List<ProductType> queryProductTypeList(JSONObject param) {
        QueryWrapper<ProductType> qw = new QueryWrapper<>();
        qw.eq("flag", 1);
        qw.eq(param.getString("typeLevel") != null, "type_level", param.getString("typeLevel"));
        return typeMapper.selectList(qw);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer addProductType(ProductType type) {
        type.setProductTypeId(IDUtils.generateID());
        return typeMapper.insert(type);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer updateProductType(ProductType type) {
        return typeMapper.updateById(type);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer deleteProductType(ProductType type) {
        type.setFlag(0);
        return typeMapper.updateById(type);
    }

}
