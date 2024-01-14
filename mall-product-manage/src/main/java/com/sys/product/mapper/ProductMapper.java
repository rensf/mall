package com.sys.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sys.product.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author rensf
 * @date 2021/3/26
 */
@Mapper
@Repository
public interface ProductMapper extends BaseMapper<Product> {

    /**
     * 分页查询产品列表
     *
     * @param page
     * @param product
     * @return
     */
    @Select("<script>" +
            "select " +
            "a.*, " +
            "group_concat(c.product_type_id) as typeId, " +
            "group_concat(c.product_type_name) as typeName, " +
            "group_concat(distinct d.product_image) as homeImage, " +
            "group_concat(distinct e.product_image) as image " +
            "from " +
            "td_b_product a " +
            "left join tr_b_product_product_type b " +
            "on " +
            "a.product_id = b.product_id " +
            "and b.flag = 1 " +
            "left join td_b_product_type c " +
            "on " +
            "b.product_type_id = c.product_type_id " +
            "and c.flag = 1 " +
            "left join td_b_product_image d " +
            "on " +
            "a.product_id = d.product_id " +
            "and d.flag = 1 " +
            "and d.image_position = 'home' " +
            "left join td_b_product_image e " +
            "on " +
            "a.product_id = e.product_id " +
            "and e.flag = 1 " +
            "and e.image_position is null " +
            "where " +
            "a.flag = 1 " +
            "<if test='product.productFirst == 1'> " +
            "   and a.product_first = #{product.productFirst} " +
            "</if> " +
            "group by " +
            "a.product_id " +
            "order by " +
            "a.product_id desc " +
            "</script>")
    IPage<Product> queryProductListByPage(Page<Product> page, Product product);

}
