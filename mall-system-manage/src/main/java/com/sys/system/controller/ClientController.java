package com.sys.system.controller;

import com.sys.common.dto.ClientAuthDTO;
import com.sys.common.result.Result;
import com.sys.system.entity.Client;
import com.sys.system.service.IClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author rensf
 * @date 2023/1/16
 */
@RestController
@RequestMapping("/client")
@Api(tags = "用户管理")
public class ClientController {

    @Resource
    private IClientService clientService;

    @GetMapping("/getOAuth2ClientById")
    @ApiOperation(value = "通过ID获取OAuth2客户端认证信息", notes = "Feign", hidden = true)
    public Result<ClientAuthDTO> getOAuth2ClientById(@RequestParam String clientId) {
        Client client = clientService.getById(clientId);
        ClientAuthDTO clientAuthDTO = new ClientAuthDTO();
        BeanUtils.copyProperties(client, clientAuthDTO);
        return Result.success(clientAuthDTO);
    }

}
