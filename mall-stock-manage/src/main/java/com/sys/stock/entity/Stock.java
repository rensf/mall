package com.sys.stock.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 库存 实体
 *
 * @author rensf
 * @date 2024/2/22
 */
@Data
@Component
@TableName("td_b_stock")
public class Stock {

    /**
     * ID
     */
    @TableId
    private String stockId;

    /**
     * 产品ID
     */
    private String productId;

    /**
     * 产品属性ID组
     */
    private String productAttrIds;

    /**
     * 库存名称
     */
    private String stockName;

    /**
     * 库存规格
     */
    private String stockSpecs;

    /**
     * 库存单位
     */
    private String stockUnit;

    /**
     * 库存数量
     */
    private Integer stockNums;

    /**
     * 库存价格
     */
    private String stockPrice;

    /**
     * 库存折扣价格
     */
    private String stockDiscountPrice;

    /**
     * 生效标志
     */
    private Integer flag = 1;

    /**
     * 产品属性ID列表
     */
    @TableField(exist = false)
    private List<String> productAttrIdList;

    /**
     * 产品规格列表
     */
    @TableField(exist = false)
    private List<String> productSpecList;

}
