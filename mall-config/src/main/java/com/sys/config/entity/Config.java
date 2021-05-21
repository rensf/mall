package com.sys.config.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author rensf
 * @date 2021/5/13 11:35
 */
@Data
@Component
@TableName("td_sys_config")
public class Config implements Serializable {

    private static final long serialVersionUID = -1676580970171585581L;

    /**
     * ID
     */
    @TableId
    private String id;

    /**
     * 键
     */
    private String configKey;

    /**
     * 值
     */
    private String configValue;

    /**
     * 系统名称
     */
    private String application;

    /**
     * 系统环境
     */
    private String profile;

    /**
     * 读取分支
     */
    private String label;

    /**
     * 生效标志
     */
    private Integer flag = 1;

}
