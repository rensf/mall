package com.sys.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sys.user.entity.Address;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 地址 映射层
 *
 * @author rensf
 * @date 2021/6/25
 */
@Mapper
@Repository
public interface AddressMapper extends BaseMapper<Address> {
}
