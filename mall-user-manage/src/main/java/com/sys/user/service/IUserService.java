package com.sys.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sys.common.exception.GlobalException;
import com.sys.user.entity.Address;
import com.sys.user.entity.User;

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
     * @param qrcodeId
     * @param userId
     * @return
     */
    String loginByQrcode(String qrcodeId, String userId);

    /**
     * 普通登录
     * @param loginInfo
     * @return
     * @throws GlobalException
     */
    User loginByNormal(String loginInfo);

    /**
     * 获取登录用户信息
     * @return
     */
    User getLoginUserInfo();

    /**
     * 分页查询用户列表
     * @return
     */
    List<User> queryUserListByPage();

    /**
     * 查询地址列表通过用户ID
     * @param userId 用户ID
     * @return List<Address> 地址列表
     */
    List<Address> queryAddressListByUserId(String userId);

    /**
     * 新建用户
     * @param user
     * @return
     */
    Integer addUser(User user);
}
