package com.demo.springboot.rabbitmq.util;

import java.util.Random;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 通用工具类
 * @date 2021/4/12 10:44
 * @see
 */
public class CommonUtils {

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 生成一个[begin, end]的随机数
     * @date 2021/4/12 10:45
     */
    public static Integer nextInt(int begin, int end) {

        Random random = new Random();
        // nextInt(X):返回【0，X）的随机整数
        int ret = random.nextInt(end - begin + 1) + begin;
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(nextInt(1, 100));
    }
}
