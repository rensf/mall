package com.sys.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 地址 实体
 *
 * @author rensf
 * @date 2021/6/4
 */
@Data
@Component
@TableName("td_b_address")
public class Address {

    /**
     * ID
     */
    @TableId
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
     * 联系人姓名
     */
    private String concatName;

    /**
     * 联系人电话
     */
    private String concatTel;

    /**
     * 生效标志
     */
    private Integer flag = 1;

}
