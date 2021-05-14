package com.sys.config.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sys.config.entity.Config;
import com.sys.config.mapper.ConfigMapper;
import com.sys.config.service.IConfigService;
import com.sys.config.util.ToJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author rensf
 * @date 2021/5/13 11:31
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements IConfigService {

    private ConfigMapper configMapper;

    @Autowired
    public ConfigServiceImpl(ConfigMapper configMapper) {
        this.configMapper = configMapper;
    }

    @Override
    public IPage<Config> queryConfigListByPage(Map param) {
        JSONObject jsonParam = ToJson.toJson(param);
        Page<Config> page = new Page<>();
        page.setSize(jsonParam.getLong("size"));
        page.setTotal(jsonParam.getLong("total"));
        page.setCurrent(jsonParam.getLong("current"));
        QueryWrapper<Config> qw = new QueryWrapper<>();
        qw.select("id", "`key`", "`value`", "application", "profile", "label", "flag");
        qw.eq("flag", 1);
        qw.like(jsonParam.getString("application") != null, "application", jsonParam.getString("application"));
        return configMapper.selectPage(page, qw);
    }

    @Override
    public Integer updateConfig(Config config) {
        return configMapper.updateById(config);
    }

    @Override
    public Integer deleteConfig(Config config) {
        config.setFlag(0);
        return configMapper.updateById(config);
    }

}
