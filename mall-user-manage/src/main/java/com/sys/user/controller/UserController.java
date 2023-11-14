package com.sys.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.sys.common.dto.UserAuthDTO;
import com.sys.common.exception.GlobalException;
import com.sys.common.util.TokenUtils;
import com.sys.common.result.Result;
import com.sys.user.entity.User;
import com.sys.user.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author rensf
 * @date 2021/5/10 15:39
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
public class UserController {

    @Resource
    private IUserService userService;

    @GetMapping("/loginByNormal")
    @ApiOperation("普通登录")
    public Result<UserAuthDTO> loginByNormal(@RequestParam String userName) {
        User user = userService.loginByNormal(userName);
        UserAuthDTO result = new UserAuthDTO();
        BeanUtils.copyProperties(user, result);
        return Result.success(result);
    }

    @GetMapping("/generateQrcode")
    @ApiOperation("生成二维码")
    public Result<Object> generateQrcode() throws IOException {
        return Result.success(userService.generateQrcode());
    }

    @GetMapping("/loginByQrcode")
    @ApiOperation("二维码扫描登录")
    public Result loginByQrcode() {
        Result result = new Result();
        return result;
    }

    @GetMapping("/getLoginUserInfo")
    @ApiOperation("获取登录用户信息")
    public Result getLoginUserInfo() {
        return Result.success(userService.getLoginUserInfo());
    }

    @GetMapping("/queryUserList")
    @ApiOperation("查询用户列表")
    public Result queryUserList() {
        Result result = new Result();
        return result;
    }

    @PostMapping("/addUser")
    @ApiOperation("新增用户")
    public Result<Object> addUser(User user) {
        return Result.success(userService.addUser(user));
    }

    @PostMapping("/updateUser")
    @ApiOperation("更新用户信息")
    public Result updateUser() {
        Result result = new Result();
        return result;
    }

}
