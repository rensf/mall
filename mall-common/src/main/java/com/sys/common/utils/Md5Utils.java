package com.sys.common.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author rensf
 * @date 2021/5/21 16:30
 */
public class Md5Utils {

    private static final String SECRET_KEY = "mall";

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

    public static String makePwd(String name, String password) {
        return Md5Utils.encryption(name.toUpperCase() + password + Md5Utils.SECRET_KEY);
    }

}
