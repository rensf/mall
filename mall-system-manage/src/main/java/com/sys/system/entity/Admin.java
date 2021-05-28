package com.sys.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author rensf
 * @date 2021/5/21 11:51
 */
@Data
@TableName("td_b_admin")
public class Admin {

    /**
     * ID
     */
    @TableId
    private String adminId;

    private String adminName;

    private String password;

    private String adminTel;

    private String adminEmail;

    private Integer flag = 1;

}
