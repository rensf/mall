package com.sys.auth.sevice.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sys.auth.entity.Client;
import com.sys.auth.mapper.ClientMapper;
import com.sys.auth.sevice.IClientService;
import org.springframework.stereotype.Service;

/**
 * @author rensf
 * @date 2022/12/30
 */
@Service
public class ClientServiceImpl extends ServiceImpl<ClientMapper, Client> implements IClientService {

}
