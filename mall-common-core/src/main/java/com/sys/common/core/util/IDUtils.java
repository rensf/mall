package com.sys.common.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author rensf
 * @date 2021/5/10
 */
public class IDUtils {

    public static synchronized String generateID() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(new Date()) + getRandom();
    }

    private static String getRandom() {
        Random rm = new Random();
        // 获得随机数
        double pross = (1 + rm.nextDouble()) * Math.pow(10, 4);
        // 将随机数转换为字符串
        String fixString = String.valueOf(pross);
        // 返回固定长度的随机数字符串
        return fixString.substring(1, 4 + 1);
    }

}
