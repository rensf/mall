package com.sys.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author rensf
 * @date 2021/6/8 17:21
 */
@Data
@Component
@TableName("td_b_cart")
public class Cart implements Serializable {

    private static final long serialVersionUID = 8191680091595709705L;

    /**
     * ID
     */
    private String cartId;

    /**
     * 生效标志
     */
    private Integer flag = 1;

}
