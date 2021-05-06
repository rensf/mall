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
 */
@Mapper
@Repository
public interface ProductMapper extends BaseMapper<Product> {

    /**
     * @options 分页查询产品列表
     * @param page
     * @return
     */
    @Select("SELECT \n" +
            "  a.*,\n" +
            "  GROUP_CONCAT(c.`product_type_id`) AS `typeId`,\n" +
            "  GROUP_CONCAT(c.`type_name`) AS `typeName`,\n" +
            "  GROUP_CONCAT(d.`product_image`) AS `homeImage`,\n" +
            "  GROUP_CONCAT(e.`product_image`) AS `image`\n" +
            "FROM\n" +
            "  td_b_product a \n" +
            "  LEFT JOIN tr_b_product_type b \n" +
            "    ON a.`product_id` = b.`product_id` \n" +
            "    AND b.`flag` = 1 \n" +
            "  LEFT JOIN td_b_product_type c \n" +
            "    ON b.`product_type_id` = c.`product_type_id` \n" +
            "    AND c.`flag` = 1 \n" +
            "  LEFT JOIN td_b_product_image d \n" +
            "    ON a.`product_id` = d.`product_id` \n" +
            "    AND d.`flag` = 1 \n" +
            "    AND d.`image_position` = 'home' \n" +
            "  LEFT JOIN td_b_product_image e \n" +
            "    ON a.`product_id` = e.`product_id` \n" +
            "    AND e.`flag` = 1 \n" +
            "    AND e.`image_position` IS NULL \n" +
            "WHERE a.`flag` = 1 \n" +
            "GROUP BY a.`product_id`")
    IPage<Product> queryProductListByPage(Page page);

}
