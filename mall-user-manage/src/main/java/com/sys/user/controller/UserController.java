package com.sys.user.controller;

import com.sys.common.web.dto.UserAuthDTO;
import com.sys.common.core.result.Result;
import com.sys.user.entity.Address;
import com.sys.user.entity.User;
import com.sys.user.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * 用户 控制层
 *
 * @author rensf
 * @date 2021/5/10
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

    @GetMapping("/queryAddressListByUserId")
    @ApiOperation("查询地址列表通过用户ID")
    public Result<List<Address>> queryAddressListByUserId(String userId) {
        List<Address> addressList = userService.queryAddressListByUserId(userId);
        return Result.success(addressList);
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
