package com.demo.springboot.web.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 字符串处理工具
 * @date 2021/1/12 18:08
 * @see
 */
public class StringUtils {

    /**
     * @param prefix 前缀
     * @param date   日期
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 生成30位的token
     * @date 2021/1/12 18:09
     */
    public static String generateToken(String prefix, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMss");
        String suffix = UUID.randomUUID().toString().replaceAll("-", "");
        String ret = prefix + sdf.format(date) + suffix;
        return ret.substring(0, 30);
    }


    public static void main(String[] args) {

        Date date = new Date();
        String token = generateToken("token", date);
        System.out.println("==============>>>token:" + token);

    }
}
