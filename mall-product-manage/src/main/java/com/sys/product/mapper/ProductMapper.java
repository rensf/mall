package com.sys.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sys.common.mybatis.handler.StringToListTypeHandler;
import com.sys.product.entity.Product;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

/**
 * 产品 映射层
 *
 * @author rensf
 * @date 2021/3/26
 */
@Mapper
@Repository
public interface ProductMapper extends BaseMapper<Product> {

    /**
     * 分页查询产品列表
     *
     * @param page 分页
     * @param product 条件
     * @return 结果
     */
    @Select("<script>" +
            "select " +
            "a.product_id, " +
            "a.product_name, " +
            "a.product_model, " +
            "a.product_unit, " +
            "a.product_price, " +
            "a.product_discount_price, " +
            "a.product_detail, " +
            "a.product_first, " +
            "group_concat(c.product_type_id) as type_ids, " +
            "group_concat(c.product_type_name) as type_names, " +
            "group_concat(distinct d.product_image) as home_images, " +
            "group_concat(distinct e.product_image) as images " +
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
            "    and a.product_first = #{product.productFirst} " +
            "</if> " +
            "<if test='product.productTypeId != null and product.productTypeId != \"\"'> " +
            "    and c.product_type_id = #{product.productTypeId} " +
            "</if> " +
            "<if test='product.productName != null and product.productName != \"\"'> " +
            "    and a.product_name like concat('%', #{product.productName}, '%') " +
            "</if> " +
            "group by " +
            "a.product_id " +
            "order by " +
            "a.product_id desc " +
            "</script>")
    @Result(property = "typeIds", column = "type_ids", typeHandler = StringToListTypeHandler.class)
    @Result(property = "typeNames", column = "type_names", typeHandler = StringToListTypeHandler.class)
    @Result(property = "homeImages", column = "home_images", typeHandler = StringToListTypeHandler.class)
    @Result(property = "images", column = "images", typeHandler = StringToListTypeHandler.class)
    IPage<Product> queryProductListByPage(Page<Product> page, Product product);

    /**
     * 通过产品ID查询产品信息
     * @param productId 产品ID
     * @return 结果
     */
    @Select("select " +
            "a.product_id, " +
            "a.product_name, " +
            "a.product_model, " +
            "a.product_unit, " +
            "a.product_price, " +
            "a.product_discount_price, " +
            "a.product_detail, " +
            "a.product_first, " +
            "group_concat(c.product_type_id) as type_ids, " +
            "group_concat(c.product_type_name) as type_names, " +
            "group_concat(distinct d.product_image) as home_images, " +
            "group_concat(distinct e.product_image) as images " +
            "from td_b_product a " +
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
            "where a.flag = 1 " +
            "and a.product_id = #{productId} " +
            "group by " +
            "a.product_id " +
            "order by " +
            "a.product_id desc ")
    @Result(property = "typeIds", column = "type_ids", typeHandler = StringToListTypeHandler.class)
    @Result(property = "typeNames", column = "type_names", typeHandler = StringToListTypeHandler.class)
    @Result(property = "homeImages", column = "home_images", typeHandler = StringToListTypeHandler.class)
    @Result(property = "images", column = "images", typeHandler = StringToListTypeHandler.class)
    @Result(property = "productAttrs", column = "product_id", jdbcType = JdbcType.ARRAY, many = @Many(select = "com.sys.product.mapper.ProductAttrMapper.queryProductAttrs"))
    Product queryProductById(String productId);

}
