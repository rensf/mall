package com.sys.auth.security.detail.clientdetail;

import com.sys.auth.entity.Client;
import com.sys.auth.sevice.IClientService;
import com.sys.common.enums.PasswordEncodeEnum;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 客户端信息
 *
 * @author rensf
 * @date 2022/12/29
 */
@Service
public class ClientDetailsServiceImpl implements ClientDetailsService {

    @Resource
    private IClientService clientService;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        Client client = clientService.getById(clientId);
        if (Objects.nonNull(client)) {
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
