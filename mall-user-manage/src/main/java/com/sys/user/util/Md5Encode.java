package com.sys.user.util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * @author rensf
 * @date 2021/6/4 10:46
 */
public class Md5Encode {

    private static final String SECRET_KEY = "user";

    private static String encryption(String str) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] btInput = str.getBytes(StandardCharsets.UTF_8);
        md.update(btInput);
        return new BigInteger(1, md.digest()).toString(16);
    }

    public static String makePwd(String name, String password) throws Exception {
        return Md5Encode.encryption(name.toUpperCase() + password + Md5Encode.SECRET_KEY);
    }

}
