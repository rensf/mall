package com.sys.common.core.util;

import com.sys.common.core.constant.SecurityConstants;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author rensf
 * @date 2021/5/21
 */
public class MD5Utils {

    private static String encryption(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] btInput = str.getBytes(StandardCharsets.UTF_8);
            md.update(btInput);
            return new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String makePwd(String password) {
        return MD5Utils.encryption(password + SecurityConstants.SECRET_KEY);
    }

}
