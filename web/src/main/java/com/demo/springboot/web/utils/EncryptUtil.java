package com.demo.springboot.web.utils;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 基于Shiro框架提供的加密
 * @date 2021/1/8 20:00
 * @see
 */
public class EncryptUtil {

    //盐
    private String salt = "salt";
    //加密次数
    private int hashIterations = 10;

    /**
     * base64 算法加密：
     *
     * @param password 明文
     * @return 密文
     */
    public String base64Encrypt(String password) {
        String encodeToString = Base64.encodeToString(password.getBytes());
        return encodeToString;
    }

    /**
     * base64 算法解密：
     *
     * @param password 密文
     * @return 明文
     */
    public String base64Decrypt(String password) {
        String decodeToString = Base64.decodeToString(password);
        return decodeToString;
    }

    /**
     * @param password 明文
     * @return 密文
     * @author Wenyi Cao
     * @version 1.0
     * @description md5算法 加密
     * @date 2021/1/8 20:09
     */
    public String md5Encrypt(String password) {
        //MD5普通加密
        String encodeToString = new Md5Hash(password).toString();

//        //md5加密转base64位编码或者16进制编码
//        String md5Base64 = new Md5Hash(password).toBase64();
//        String md5Hex = new Md5Hash(password).toHex();
//        System.out.println(md5Base64);
//        System.out.println(md5Hex);
//
//        //md5加密，加密内容source,带盐加密salt，还可以指定加密次数：hashIterations
//        md5Base64 = new Md5Hash("asdf", "123", 5).toBase64();
//        System.out.println(md5Base64);
        return encodeToString;
    }

    /**
     * sha加密
     * SHA1,SHA256,SHA512
     *
     * @param password 明文
     * @param type     算法类型
     * @return 密文
     */
    public String shaEncrpt(String password, String type) {
        if ("sha1".equals(type)) {
            String sha1hash = new Sha1Hash(password, salt, hashIterations).toBase64();
            return sha1hash;
        } else if ("sha256".equals(type)) {
            String sha256hash = new Sha256Hash(password, salt, hashIterations).toBase64();
            return sha256hash;
        } else if ("sha512".equals(type)) {
            String sha512hash = new Sha512Hash(password, salt, hashIterations).toBase64();
            return sha512hash;
        } else {
            return "error";
        }
    }

    /**
     * 通用加密：SimpleHash,将算法名称添加到方法即可
     *
     * @param algorithmName 算法名称，例如：sha1,sha-256,sha-512,md5
     * @param password      明文
     * @return 密文, error表示错误
     */
    public String simpleHashEncrpt(String password, String algorithmName) {

        List<String> algList = new ArrayList<String>(5);
        algList.add("md5");
        algList.add("sha1");
        algList.add("sha-256");
        algList.add("sha-512");
        if (!algList.contains(algorithmName)) {
            return "error";
        }
        String ret = new SimpleHash(algorithmName, password, salt, hashIterations).toBase64();
        return ret;
    }

}
