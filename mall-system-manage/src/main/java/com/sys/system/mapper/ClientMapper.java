package com.sys.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sys.system.entity.Client;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author rensf
 * @date 2022/12/30
 */
@Mapper
@Repository
public interface ClientMapper extends BaseMapper<Client> {
}
