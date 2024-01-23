package com.sys.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sys.product.entity.ProductImage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 产品图片 映射层
 *
 * @author rensf
 * @date 2024/1/23
 */
@Mapper
@Repository
public interface ProductImageMapper extends BaseMapper<ProductImage> {

}
