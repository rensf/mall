package com.sys.auth.security.detail.clientdetail;

import com.sys.auth.api.AuthClientFeign;
import com.sys.common.dto.ClientAuthDTO;
import com.sys.common.enums.PasswordEncodeEnum;
import com.sys.common.result.Result;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 客户端信息
 * @author rensf
 * @date 2022/12/29
 */
@Service
public class ClientDetailsServiceImpl implements ClientDetailsService {

    @Resource
    private AuthClientFeign authClientFeign;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        Result<ClientAuthDTO> result = authClientFeign.getOAuth2ClientById(clientId);
        if (Result.success().getCode().equals(result.getCode())) {
            ClientAuthDTO client = result.getResult();
            BaseClientDetails clientDetails = new BaseClientDetails(
                client.getClientId(),
                client.getResourceIds(),
                client.getScope(),
                client.getGrantType(),
                client.getAuthorities(),
                client.getRedirectUri()
            );
            clientDetails.setClientSecret(PasswordEncodeEnum.NOOP.getPrefix () + client.getClientSecret());
            clientDetails.setAccessTokenValiditySeconds(client.getAccessTokenValidity());
            clientDetails.setRefreshTokenValiditySeconds(client.getRefreshTokenValidity());
            return clientDetails;
        } else {
            throw new NoSuchClientException("没有找到此次请求的客户端！客户端ID：" + clientId);
        }
    }

}
