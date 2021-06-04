package com.sys.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author rensf
 * @date 2021/6/4 17:33
 */
@Data
@Component
@TableName("td_b_address")
public class Address {

    /**
     * ID
     */
    private String addressId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 省份/直辖市
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 区/县
     */
    private String county;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 生效标志
     */
    private Integer flag = 1;

}
