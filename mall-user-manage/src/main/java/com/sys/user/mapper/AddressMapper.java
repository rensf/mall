package com.sys.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sys.user.entity.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 地址 映射层
 *
 * @author rensf
 * @date 2021/6/25
 */
@Mapper
@Repository
public interface AddressMapper extends BaseMapper<Address> {

    @Select("select " +
            "tba.address_id, " +
            "tba.user_id, " +
            "tba.province, " +
            "tba.city, " +
            "tba.county, " +
            "tba.address, " +
            "tba.concat_name, " +
            "tba.concat_tel, " +
            "tba.defaulted " +
            "from " +
            "td_b_address tba " +
            "where tba.flag = 1 " +
            "and tba.user_id = #{userId} " +
            "order by tba.defaulted desc")
    List<Address> queryAddressListByUserId(String userId);

}
