package com.demo.springboot.redissionwithspringboot.utils;

import cn.hutool.crypto.SecureUtil;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description md5 工具类
 * @date 2021/12/6 17:19
 * @see
 */
public class Md5Util {

    /**
     * @param text 明文
     * @param key  密钥
     * @return 密文
     */
    // 带秘钥加密
    public static String md5(String text, String key) throws Exception {
        // 加密后的字符串
//
//        System.out.println("MD5加密后的字符串为:" + md5str);
//        return md5str;
        return null;
    }

    // 不带秘钥加密
    public static String md52(String text) throws Exception {
        // 加密后的字符串
        String md5str = SecureUtil.md5(text);
        System.out.println("MD52加密后的字符串为:" + md5str + "\t长度：" + md5str.length());
        return md5str;
    }

    /**
     * MD5验证方法
     *
     * @param text 明文
     * @param key  密钥
     * @param md5  密文
     */
    // 根据传入的密钥进行验证
    public static boolean verify(String text, String key, String md5) throws Exception {
        String md5str = md5(text, key);
        if (md5str.equalsIgnoreCase(md5)) {
            System.out.println("MD5验证通过");
            return true;
        }
        return false;
    }

    // 测试
    public static void main(String[] args) throws Exception {
        // String str =
        // "181115.041650.10.88.168.148.2665.2419425653_1";181115.040908.10.88.181.118.3013.1655327821_1
        String str = "181115.040908.10.88.181.118.3013.1655327821_1";
        System.out.println("加密的字符串：" + str + "\t长度：" + str.length());
        Md5Util.md52(str);
    }

}
