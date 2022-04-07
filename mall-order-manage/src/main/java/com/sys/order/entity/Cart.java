package com.sys.order.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * @author rensf
 * @date 2021/6/8 17:21
 */
@Data
@Component
public class Cart implements Serializable {

    private static final long serialVersionUID = 8191680091595709705L;

    /**
     * ID
     */
    private String cartId;

    /**
     * 购物车产品列表
     */
    private List<CartProduct> productList;

}
