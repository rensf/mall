package com.sys.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 系统管理员 实体
 * @author rensf
 * @date 2021/5/21
 */
@Data
@Component
@TableName("td_sys_admin")
public class Admin {

    /**
     * ID
     */
    @TableId
    private String adminId;

    /**
     * 管理员姓名
     */
    private String adminName;

    /**
     * 密码
     */
    private String password;

    /**
     * 管理员手机号
     */
    private String adminTel;

    /**
     * 管理员邮箱
     */
    private String adminEmail;

    /**
     * 生效标志
     */
    private Integer flag = 1;

    /**
     * token
     */
    @TableField(exist = false)
    private String token;
}
