package com.sys.user.service.impl;

import cn.hutool.core.codec.Base64;
import cn.hutool.extra.qrcode.QrCodeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sys.common.core.constant.RegexConstants;
import com.sys.common.core.util.IDUtils;
import com.sys.common.web.util.TokenUtils;
import com.sys.user.entity.Address;
import com.sys.user.entity.User;
import com.sys.user.mapper.AddressMapper;
import com.sys.user.mapper.UserMapper;
import com.sys.user.service.IUserService;
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
 * 用户 服务层
 *
 * @author rensf
 * @date 2021/6/4
 */
@Service
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
        String qrcodeId = IDUtils.generateID();
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
            // 将token和user传回前台，并进行页面跳转
            return null;
        } else {

        }
        return null;
    }

    @Override
    public User loginByNormal(String loginInfo) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        if (loginInfo.matches(RegexConstants.TEL_REGEX)) {
            qw.eq("user_tel", loginInfo);
        } else if (loginInfo.matches(RegexConstants.EMAIL_REGEX)) {
            qw.eq("user_email", loginInfo);
        } else {
            qw.eq("user_name", loginInfo);
        }
        qw.eq("flag", 1);
        return this.baseMapper.selectOne(qw);
    }

    @Override
    public User getLoginUserInfo() {
        return this.baseMapper.queryUserById(TokenUtils.getJwtPayload().getString("userId"));
    }

    @Override
    public List<User> queryUserListByPage() {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("flag", 1);
        return userMapper.selectList(qw);
    }

    @Override
    public List<Address> queryAddressListByUserId(String userId) {
        QueryWrapper<Address> qw = new QueryWrapper<>();
        qw.eq("user_id", userId);
        qw.eq("flag", 1);
        return addressMapper.selectList(qw);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer addUser(User user) {
        user.setUserId(IDUtils.generateID());
        return userMapper.insert(user);
    }

}
