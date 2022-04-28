package com.sys.system.controller;

import com.sys.common.vo.Result;
import com.sys.system.entity.Admin;
import com.sys.system.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author rensf
 * @date 2021/5/21 11:48
 */
@RestController
@RequestMapping("/system")
@Api(tags = "系统管理")
public class SystemController {

    @Resource
    private IAdminService adminService;

    @GetMapping("/loginByNormal")
    @ApiOperation("用户名密码登录")
    public Result<Admin> loginByNormal(@ApiParam("用户名") @RequestParam String adminName, @ApiParam("密码") @RequestParam String password) throws Exception {
        Result<Admin> result = new Result<>();
        Admin loginAdmin = new Admin();
        loginAdmin.setAdminName(adminName);
        loginAdmin.setPassword(password);
        result.setResult(adminService.loginByNormal(loginAdmin));
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
