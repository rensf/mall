package com.sys.user.controller;

import com.sys.user.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rensf
 * @date 2021/5/10 15:39
 */
@RestController
@RequestMapping("/user")
@Api("用户管理")
public class UserController {

    @GetMapping("/loginByNormal")
    @ApiOperation("用户名密码登录")
    public Result loginByNormal() {
        Result result = new Result();
        return result;
    }

    @GetMapping("/queryUserList")
    @ApiOperation("查询用户列表")
    public Result queryUserList() {
        Result result = new Result();
        return result;
    }

    @PostMapping("/updateUser")
    @ApiOperation("更新用户信息")
    public Result updateUser() {
        Result result = new Result();
        return result;
    }

}
