package com.sys.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sys.product.entity.ProductProductType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author rensf
 * @date 2022/1/27 10:23
 */
@Mapper
@Repository
public interface ProductProductTypeMapper extends BaseMapper<ProductProductType> {
}
