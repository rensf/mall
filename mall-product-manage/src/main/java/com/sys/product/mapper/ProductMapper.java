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
            "tbp.product_id, " +
            "tbp.product_name, " +
            "tbp.product_model, " +
            "tbp.product_unit, " +
            "tbp.product_price, " +
            "tbp.product_discount_price, " +
            "tbp.product_detail, " +
            "tbp.product_first, " +
            "group_concat(tbpt.product_type_id) as type_id_list, " +
            "group_concat(tbpt.product_type_name) as type_name_list, " +
            "group_concat(distinct tbpia.product_image) as home_image_list, " +
            "group_concat(distinct tbpib.product_image) as image_list, " +
            "tbs.stock_id, " +
            "tbs.stock_specs, " +
            "tbs.stock_unit " +
            "from td_b_product tbp " +
            "left join tr_b_product_product_type tbppt on tbp.product_id = tbppt.product_id and tbppt.flag = 1 " +
            "left join td_b_product_type tbpt on tbppt.product_type_id = tbpt.product_type_id and tbpt.flag = 1 " +
            "left join td_b_product_image tbpia on tbp.product_id = tbpia.product_id and tbpia.flag = 1 and tbpia.image_position = 'home' " +
            "left join td_b_product_image tbpib on tbp.product_id = tbpib.product_id and tbpib.flag = 1 and tbpib.image_position is null " +
            "left join td_b_stock tbs on tbp.product_id = tbs.product_id and tbs.flag = 1 and tbs.sort_num = 1 " +
            "where tbp.flag = 1 " +
            "<if test='product.productFirst == 1'> " +
            "    and tbp.product_first = #{product.productFirst} " +
            "</if> " +
            "<if test='product.productTypeId != null and product.productTypeId != \"\"'> " +
            "    and tbpt.product_type_id = #{product.productTypeId} " +
            "</if> " +
            "<if test='product.productName != null and product.productName != \"\"'> " +
            "    and tbp.product_name like concat('%', #{product.productName}, '%') " +
            "</if> " +
            "group by tbp.product_id " +
            "order by tbp.product_id desc " +
            "</script>")
    @Result(property = "typeIdList", column = "type_id_list", typeHandler = StringToListTypeHandler.class)
    @Result(property = "typeNameList", column = "type_name_list", typeHandler = StringToListTypeHandler.class)
    @Result(property = "homeImageList", column = "home_image_list", typeHandler = StringToListTypeHandler.class)
    @Result(property = "imageList", column = "image_list", typeHandler = StringToListTypeHandler.class)
    IPage<Product> queryProductListByPage(Page<Product> page, Product product);

    /**
     * 通过产品ID查询产品信息
     *
     * @param productId 产品ID
     * @return 结果
     */
    @Select("select " +
            "tbp.product_id, " +
            "tbp.product_name, " +
            "tbp.product_model, " +
            "tbp.product_unit, " +
            "tbp.product_price, " +
            "tbp.product_discount_price, " +
            "tbp.product_detail, " +
            "tbp.product_first, " +
            "group_concat(tbpt.product_type_id) as type_id_list, " +
            "group_concat(tbpt.product_type_name) as type_name_list, " +
            "group_concat(distinct tbpia.product_image) as home_image_list, " +
            "group_concat(distinct tbpib.product_image) as image_list, " +
            "tbs.stock_id, " +
            "tbs.stock_specs, " +
            "tbs.stock_unit " +
            "from td_b_product tbp " +
            "left join tr_b_product_product_type tbppt on tbp.product_id = tbppt.product_id and tbppt.flag = 1 " +
            "left join td_b_product_type tbpt on tbppt.product_type_id = tbpt.product_type_id and tbpt.flag = 1 " +
            "left join td_b_product_image tbpia on tbp.product_id = tbpia.product_id and tbpia.flag = 1 and tbpia.image_position = 'home' " +
            "left join td_b_product_image tbpib on tbp.product_id = tbpib.product_id and tbpib.flag = 1 and tbpib.image_position is null " +
            "left join td_b_stock tbs on tbp.product_id = tbs.product_id and tbs.flag = 1 and tbs.sort_num = 1 " +
            "where tbp.flag = 1 " +
            "and tbp.product_id = #{productId} " +
            "group by tbp.product_id " +
            "order by tbp.product_id desc ")
    @Result(property = "typeIdList", column = "type_id_list", typeHandler = StringToListTypeHandler.class)
    @Result(property = "typeNameList", column = "type_name_list", typeHandler = StringToListTypeHandler.class)
    @Result(property = "homeImageList", column = "home_image_list", typeHandler = StringToListTypeHandler.class)
    @Result(property = "imageList", column = "image_list", typeHandler = StringToListTypeHandler.class)
    @Result(property = "productAttrList", column = "product_id", jdbcType = JdbcType.ARRAY, many = @Many(select = "com.sys.product.mapper.ProductAttrMapper.queryProductAttrList"))
    Product queryProductById(String productId);

}
