package com.sys.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sys.order.entity.Cart;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author rensf
 * @date 2021/6/8 20:20
 */
@Mapper
@Repository
public interface CartMapper extends BaseMapper<Cart> {

}
