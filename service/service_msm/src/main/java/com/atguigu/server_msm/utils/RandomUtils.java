package com.atguigu.server_msm.utils;

import java.util.Random;

/**
 * 获取随机数
 */
public class RandomUtils {

    public static int getRandom() {
        Random random = new Random();
        int value = random.nextInt(900000) + 100000;
        System.out.println(value);
        return value;
    }
}
