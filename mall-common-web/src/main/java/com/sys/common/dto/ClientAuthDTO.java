package com.sys.common.dto;

import lombok.Data;

/**
 * 客户端认证 DTO
 * @author rensf
 * @date 2023/1/3
 */
@Data
public class ClientAuthDTO {

    /**
     * 客户端ID
     */
    private String clientId;

    /**
     * 客户端密钥
     */
    private String clientSecret;

    /**
     * 作用域
     */
    private String scope;

    /**
     * 授权类型
     */
    private String grantType;

    /**
     * 认证令牌时效（秒）
     */
    private Integer accessTokenValidity;

    /**
     * 刷新令牌时效（秒）
     */
    private Integer refreshTokenValidity;

    /**
     * 扩展信息
     */
    private String extInfo;

    /**
     * 资源ID集合
     */
    private String resourceIds;

    /**
     * 权限集合
     */
    private String authorities;

    /**
     * 回调地址
     */
    private String redirectUri;

    /**
     * 是否自动放行
     */
    private Boolean autoApprove;

}
