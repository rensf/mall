package com.sys.user.service.impl;

import cn.hutool.core.codec.Base64;
import cn.hutool.extra.qrcode.QrCodeUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sys.common.exception.GlobalException;
import com.sys.common.utils.GenerateID;
import com.sys.common.utils.Md5Utils;
import com.sys.common.utils.TokenUtils;
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

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author rensf
 * @date 2021/6/4 17:10
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private AddressMapper addressMapper;
    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 定时任务线程池，校验二维码是否过期
     */
    private static ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(5);

    @Override
    public String generateQrcode() throws IOException {
        String qrcodeId = GenerateID.generateID();
        // redis设置二维码过期时间
        redisTemplate.expire(qrcodeId, 10, TimeUnit.MINUTES);
        BufferedImage bufferedImage = QrCodeUtil.generate(qrcodeId, 100, 100);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", stream);
        String qrcode = Base64.encode(stream.toByteArray());
        stream.close();
        return qrcode;
    }

    @Override
    public String loginByQrcode(String qrcodeId, String userId) {
        // 用户扫码，将userId和qrcodeId传入后台，判断qrcodeId是否过期
        boolean isExpire = redisTemplate.hasKey(qrcodeId);
        if (isExpire) {
            // 查询user表是否有记录
            QueryWrapper<User> qw = new QueryWrapper<>();
            qw.eq("userId", userId);
            qw.eq("flag", 1);
            User user = userMapper.selectOne(qw);
            if (Objects.nonNull(user)) {

            } else {

            }
            // 生成token，存入redis
            String token = TokenUtils.generateToken(userId);
            // 将token和user传回前台，并进行页面跳转
            return null;
        } else {

        }
        return null;
    }

    @Override
    public User loginByNormal(String userName, String password) throws GlobalException {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("user_name", userName);
        qw.eq("flag", 1);
        User user = userMapper.selectOne(qw);
        if (Objects.isNull(user)) {
            throw new GlobalException("10001", "用户不存在！");
        } else {
            if (Md5Utils.makePwd(userName, password).equals(user.getPassword())) {
                return user;
            } else {
                throw new GlobalException("10000", "用户名或密码输入错误！");
            }
        }
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

    @Override
    public Integer addUser(User user) {
        user.setUserId(GenerateID.generateID());
        return userMapper.insert(user);
    }

}
