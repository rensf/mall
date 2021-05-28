package com.sys.system.controller;

import com.sys.system.common.Result;
import com.sys.system.entity.Admin;
import com.sys.system.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rensf
 * @date 2021/5/21 11:48
 */
@RestController
@RequestMapping("/system")
@Api("系统管理")
public class SystemController {

    private IAdminService adminService;

    @Autowired
    public SystemController(IAdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/loginByNormal")
    @ApiOperation("用户名密码登录")
    public Result<Admin> loginByNormal(@RequestBody Admin loginInfo) throws Exception {
        Result<Admin> result = new Result<>();
        result.setResult(adminService.loginByNormal(loginInfo));
        return result;
    }

    @PostMapping("/addAdmin")
    @ApiOperation("添加管理员")
    public Result<Integer> addAdmin(@RequestBody Admin admin) {
        Result<Integer> result = new Result<>();
        result.setResult(adminService.addAdmin(admin));
        return result;
    }

}
