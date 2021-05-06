package com.sys.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sys.product.entity.ProductType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author rensf
 */
@Mapper
@Repository
public interface ProductTypeMapper extends BaseMapper<ProductType> {

}
