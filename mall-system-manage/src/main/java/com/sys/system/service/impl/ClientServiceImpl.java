package com.sys.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sys.system.entity.Client;
import com.sys.system.mapper.ClientMapper;
import com.sys.system.service.IClientService;
import org.springframework.stereotype.Service;

/**
 * @author rensf
 * @date 2022/12/30
 */
@Service
public class ClientServiceImpl extends ServiceImpl<ClientMapper, Client> implements IClientService {

}
