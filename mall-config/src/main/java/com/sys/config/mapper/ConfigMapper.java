package com.sys.config.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sys.config.entity.Config;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 配置 映射层
 * @author rensf
 * @date 2021/5/13
 */
@Mapper
@Repository
public interface ConfigMapper extends BaseMapper<Config> {

}
