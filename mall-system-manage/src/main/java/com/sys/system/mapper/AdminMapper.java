package com.sys.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sys.system.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author rensf
 * @date 2021/5/21 15:05
 */
@Mapper
@Repository
public interface AdminMapper extends BaseMapper<Admin> {
}
