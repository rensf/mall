package com.sys.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author rensf
 * @date 2021/8/12 15:53
 */
@Data
@Component
@TableName("td_b_cart_product")
public class CartProduct implements Serializable {

    private static final long serialVersionUID = 4005273661547944182L;

    @TableId
    private String cartProductId;
}
