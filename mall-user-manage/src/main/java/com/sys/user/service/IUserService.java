package com.sys.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sys.user.entity.Address;
import com.sys.user.entity.User;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author rensf
 * @date 2021/6/4 17:10
 */
public interface IUserService extends IService<User> {

    /**
     * 生成二维码（Base64）
     * @throws IOException
     * @return
     */
    String generateQrcode() throws IOException;

    /**
     * 二维码扫描登录
     * @param response
     * @return
     */
    User loginByQrcode(HttpServletResponse response);

    /**
     * 分页查询用户列表
     * @return
     */
    List<User> queryUserListByPage();

    /**
     * 查询详细地址通过用户ID
     * @param user
     * @return
     */
    List<Address> queryAddressByUserId(User user);

}
