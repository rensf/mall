package com.sys.config.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sys.common.util.JsonUtils;
import com.sys.config.config.RefreshBusHandler;
import com.sys.config.entity.Config;
import com.sys.config.mapper.ConfigMapper;
import com.sys.config.service.IConfigService;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * 配置 服务层
 *
 * @author rensf
 * @date 2021/5/13
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements IConfigService {

    @Resource
    private ConfigMapper configMapper;

    @Resource
    private RefreshBusHandler refreshBusHandler;

    @Override
    public IPage<Config> queryConfigListByPage(Map param) {
        JSONObject jsonParam = JsonUtils.mapToJson(param);
        Page<Config> page = new Page<>();
        page.setSize(jsonParam.getLong("size"));
        page.setTotal(jsonParam.getLong("total"));
        page.setCurrent(jsonParam.getLong("current"));
        QueryWrapper<Config> qw = new QueryWrapper<>();
        qw.eq("flag", 1);
        qw.like(jsonParam.getString("application") != null, "application", jsonParam.getString("application"));
        return configMapper.selectPage(page, qw);
    }

    @Override
    public Integer addConfig(Config config) {
        return configMapper.insert(config);
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

    @Override
    public Integer refreshConfig(String application) {
//        refreshBusHandler.busRefresh();
//        return 0;
        StringBuilder url = new StringBuilder("http://127.0.0.1:8812/actuator/busrefresh/");
        if (null != application && !application.isEmpty()) {
            url.append(application);
        }
        // CloseableHttpClient会创建一个连接池
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost();
        Integer status = null;
        try {
            URI uri = new URI(url.toString());
            post.setURI(uri);
            post.setHeader("Content-Type", "application/json");
            CloseableHttpResponse response = client.execute(post);
            status = response.getStatusLine().getStatusCode();
            // 关闭连接
            response.close();
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return status;
    }

}
