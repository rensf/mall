package com.sys.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.sys.common.exception.GlobalException;
import com.sys.common.utils.TokenUtils;
import com.sys.common.vo.Result;
import com.sys.user.entity.User;
import com.sys.user.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
    @ApiOperation("用户名密码登录")
    public Result<Object> loginByNormal(String userName, String password) throws GlobalException {
        User user = userService.loginByNormal(userName, password);
        // 将用户信息放入json对象中
        JSONObject object = new JSONObject();
        object.put("token", TokenUtils.generateToken(user.getUserId()));
        object.put("userId", user.getUserId());
        object.put("userName", user.getUserName());
        object.put("userSex", user.getUserSex());
        object.put("userTel", user.getUserTel());
        object.put("userEmail", user.getUserEmail());
        return Result.success(object);
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
