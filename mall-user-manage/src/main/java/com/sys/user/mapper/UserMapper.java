package com.sys.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sys.user.entity.User;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

/**
 * 用户 映射层
 *
 * @author rensf
 * @date 2021/6/4
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户id查询用户信息
     * @param userId
     * @return
     */
    @Select("select " +
            "tbu.user_id, " +
            "tbu.user_name, " +
            "tbu.user_tel, " +
            "tbu.user_email, " +
            "tbu.user_sex " +
            "from " +
            "td_b_user tbu " +
            "where tbu.flag = 1 " +
            "and tbu.user_id = #{userId}")
    @Result(column = "user_id", property = "addresses", jdbcType = JdbcType.ARRAY, many = @Many(select = "com.sys.user.mapper.AddressMapper.queryAddressListByUserId"))
    User queryUserById(String userId);

}
