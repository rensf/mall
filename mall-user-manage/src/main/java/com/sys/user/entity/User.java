package com.sys.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户 实体
 *
 * @author rensf
 * @date 2021/5/10
 */
@Data
@Component
@TableName("td_b_user")
public class User {

    /**
     * ID
     */
    @TableId
    private String userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户手机号
     */
    private String userTel;

    /**
     * 用户邮箱
     */
    private String userEmail;

    /**
     * 用户性别
     */
    private Integer userSex;

    /**
     * 生效标志
     */
    private Integer flag = 1;

    /**
     * 地址列表
     */
    @TableField(exist = false)
    private List<Address> addresses;

}
