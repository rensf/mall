package com.sys.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sys.product.entity.ProductProductType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 产品产品类型 映射层
 *
 * @author rensf
 * @date 2022/1/27
 */
@Mapper
@Repository
public interface ProductProductTypeMapper extends BaseMapper<ProductProductType> {
}
