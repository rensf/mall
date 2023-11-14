package com.sys.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author rensf
 * @date 2022/12/29
 */
@Data
@Component
@TableName("td_sys_client")
public class Client {

    /**
     * ID
     */
    @TableId
    private String clientId;

    /**
     * 客户端密钥
     */
    @TableField("client_secret")
    private String clientSecret;

    /**
     * 作用域
     */
    @TableField("scope")
    private String scope;

    /**
     * 授权类型
     */
    @TableField("grant_type")
    private String grantType;

    /**
     * 认证令牌时效（秒）
     */
    @TableField("access_token_validity")
    private Integer accessTokenValidity;

    /**
     * 刷新令牌时效（秒）
     */
    @TableField("refresh_token_validity")
    private Integer refreshTokenValidity;

    /**
     * 扩展信息
     */
    @TableField("ext_info")
    private String extInfo;

    /**
     * 资源ID集合
     */
    @TableField("resource_ids")
    private String resourceIds;

    /**
     * 权限集合
     */
    @TableField("authorities")
    private String authorities;

    /**
     * 回调地址
     */
    @TableField("redirect_uri")
    private String redirectUri;

    /**
     * 是否自动放行
     */
    @TableField("auto_approve")
    private Boolean autoApprove;

    /**
     * 生效标志
     */
    @TableField("flag")
    private Integer flag = 0;

}
