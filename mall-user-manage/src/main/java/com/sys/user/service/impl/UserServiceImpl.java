package com.sys.user.service.impl;

import cn.hutool.core.codec.Base64;
import cn.hutool.extra.qrcode.QrCodeUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sys.common.utils.GenerateID;
import com.sys.user.entity.Address;
import com.sys.user.entity.User;
import com.sys.user.mapper.AddressMapper;
import com.sys.user.mapper.UserMapper;
import com.sys.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author rensf
 * @date 2021/6/4 17:10
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private UserMapper userMapper;
    private AddressMapper addressMapper;
    private RedisTemplate redisTemplate;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, AddressMapper addressMapper, RedisTemplate redisTemplate) {
        this.userMapper = userMapper;
        this.addressMapper = addressMapper;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public String generateQrcode() throws IOException {
        String qrcodeId = GenerateID.generateID();
        // redis设置二维码过期时间
        redisTemplate.expire(qrcodeId, 10 , TimeUnit.MINUTES);
        BufferedImage bufferedImage = QrCodeUtil.generate(qrcodeId, 100, 100);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", stream);
        String qrcode = Base64.encode(stream.toByteArray());
        stream.close();
        return qrcode;
    }

    @Override
    public User loginByQrcode(HttpServletResponse response) {
        // 生成二维码id，根据二维码id生成二维码
        // 传到前台展示
        // 用户扫码，将用户id和二维码id绑定，用户登录成功
        // 生成token，token保存入redis
        // 将token回传到前台
        return null;
    }

    @Override
    public List<User> queryUserListByPage() {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("flag", 1);
        return userMapper.selectList(qw);
    }

    @Override
    public List<Address> queryAddressByUserId(User user) {
        QueryWrapper<Address> qw = new QueryWrapper<>();
        qw.eq("user_id", user.getUserId());
        qw.eq("flag", 1);
        return addressMapper.selectList(qw);
    }

}
