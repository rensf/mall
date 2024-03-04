package com.sys.system.controller;

import com.sys.common.web.dto.AdminAuthDTO;
import com.sys.common.core.result.Result;
import com.sys.system.entity.Admin;
import com.sys.system.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 系统管理员 控制层
 *
 * @author rensf
 * @date 2021/5/21
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "系统管理")
public class AdminController {

    @Resource
    private IAdminService adminService;

    @GetMapping("/getAdminByAdminName")
    @ApiOperation("通过用户名获取用户")
    public Result<AdminAuthDTO> getAdminByAdminName(@RequestParam String adminName) {
        return Result.success(adminService.getAdminByAdminName(adminName));
    }

    @GetMapping("/getLoginAdminInfo")
    @ApiOperation("获取登录管理员信息")
    public Result<Admin> getLoginAdminInfo() {
        return Result.success(adminService.getLoginAdminInfo());
    }

    @PostMapping("/addAdmin")
    @ApiOperation("添加管理员")
    public Result<Integer> addAdmin(@RequestBody Admin admin) {
        return Result.success(adminService.addAdmin(admin));
    }

}
