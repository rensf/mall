package com.sys.config.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sys.common.vo.Result;
import com.sys.config.entity.Config;
import com.sys.config.service.IConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author rensf
 * @date 2021/5/12 17:26
 */
@RestController
@RequestMapping("/config")
@ApiOperation("配置管理")
public class ConfigController {

    @Resource
    private IConfigService configService;

    @GetMapping("/queryConfigList")
    @ApiOperation("查询配置列表")
    public Result<IPage<Config>> queryConfigList(@RequestParam Map param) {
        Result<IPage<Config>> result = new Result<>();
        result.setResult(configService.queryConfigListByPage(param));
        return result;
    }

    @PostMapping("/addConfig")
    @ApiOperation("添加配置")
    public Result<Integer> addConfig(@RequestBody Config config) {
        Result<Integer> result = new Result<>();
        result.setResult(configService.addConfig(config));
        return result;
    }

    @PutMapping("/updateConfig")
    @ApiOperation("更新配置信息")
    public Result<Integer> updateConfig(@RequestBody Config config) {
        Result<Integer> result = new Result<>();
        result.setResult(configService.updateConfig(config));
        return result;
    }

    @DeleteMapping("/deleteConfig")
    @ApiOperation("删除配置信息")
    public Result<Integer> deleteConfig(@RequestBody Config config) {
        Result<Integer> result = new Result<>();
        result.setResult(configService.deleteConfig(config));
        return result;
    }

    @GetMapping("/refreshConfig")
    @ApiOperation("刷新配置信息")
    public Result<Integer> refreshConfig() {
        Result<Integer> result = new Result<>();
        result.setResult(configService.refreshConfig(null));
        return result;
    }

}
