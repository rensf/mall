package com.sys.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sys.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author rensf
 * @date 2021/6/4 17:11
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {



}
