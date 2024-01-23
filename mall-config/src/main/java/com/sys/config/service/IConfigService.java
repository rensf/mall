package com.sys.config.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sys.config.entity.Config;

import java.util.Map;

/**
 * 配置 服务接口
 *
 * @author rensf
 * @date 2021/5/13
 */
public interface IConfigService extends IService<Config> {

    /**
     * 分页查询配置列表
     * @param param
     * @return
     */
    IPage<Config> queryConfigListByPage(Map param);

    /**
     * 添加配置
     * @param config
     * @return
     */
    Integer addConfig(Config config);

    /**
     * 更新配置信息
     * @param config
     * @return
     */
    Integer updateConfig(Config config);

    /**
     * 删除配置信息
     * @param config
     * @return
     */
    Integer deleteConfig(Config config);

    /**
     * 刷新配置信息
     * @param application
     * @return
     */
    Integer refreshConfig(String application);

}
