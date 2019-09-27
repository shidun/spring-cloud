package com.imooc.order.util;

import java.util.Random;

/**
 * Create by Jack SD
 * Date 2019/9/26 0026 14:33
 */
public class KeyUtil {

    public static String getRadom() {
        Random random = new Random();
        Integer randoms = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + "" + randoms;
    }
}
