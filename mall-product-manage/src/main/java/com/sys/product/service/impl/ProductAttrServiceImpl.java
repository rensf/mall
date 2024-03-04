package com.sys.product.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sys.common.core.util.IDUtils;
import com.sys.product.entity.ProductAttr;
import com.sys.product.mapper.ProductAttrMapper;
import com.sys.product.service.IProductAttrService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 产品属性 服务层
 *
 * @author rensf
 * @date 2024/2/23
 */
@Service
public class ProductAttrServiceImpl extends ServiceImpl<ProductAttrMapper, ProductAttr> implements IProductAttrService {

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Boolean addProductAttr(List<ProductAttr> productAttrList) {
        String productId = productAttrList.get(0).getProductId();
        this.remove(new LambdaQueryWrapper<ProductAttr>().eq(ProductAttr::getProductId, productId));
        List<ProductAttr> newProductAttrList = productAttrList.stream()
            .flatMap(productAttr -> productAttr.getProductAttrValues().stream()
                .map(productAttrValue -> {
                    ProductAttr newProductAttr = new ProductAttr();
                    newProductAttr.setProductAttrId(IDUtils.generateID());
                    newProductAttr.setProductId(productId);
                    newProductAttr.setProductAttrName(productAttr.getProductAttrName());
                    newProductAttr.setProductAttrValue(productAttrValue);
                    return newProductAttr;
                })
            )
            .collect(Collectors.toList());
        return this.saveBatch(newProductAttrList);
    }

    @Override
    public List<ProductAttr> queryProductAttrs(String productId) {
        return this.baseMapper.queryProductAttrs(productId);
    }

}
