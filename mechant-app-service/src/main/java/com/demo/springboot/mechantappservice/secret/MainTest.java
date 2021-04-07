package com.demo.springboot.mechantappservice.secret;

import com.alibaba.fastjson.JSONObject;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 测试加密解密
 * AES 对称加密算法：加密key 和解密key 是一个key
 * RSA 非对称加密算法：公私密钥
 * @date 2021/3/16 10:52
 * @see
 */
public class MainTest {

    public static void main(String[] args) throws AesException {
        // 加密的密钥key
        String encodingAesKey = "QcXnkOzZWW7w0kgG1UxQtolNWi6hWIuC0izesp8XkcG";

        // 平台授权token
        String token = "testToken2019";
        // 时间戳
        String timestamp = "1566875499";
        String nonce = "1494577237";
        // 商户appId（认证代理）
        String appId = "wxe7cb807f01a4c762";

        // 加密串 明文
        String replyMsg = "username=zhangsan&age=12";

        WXBizMsgCrypt secretUtil = new WXBizMsgCrypt(token, encodingAesKey, appId);
        // 加密
        String mingwen = secretUtil.encryptMsg(replyMsg, timestamp, nonce);
//        {"signature":"61214f4aecc7ee0f99f253cc706100b85a0b25e6","encrypt":"WVNjjgFeHRfoWCwibRv8LqO3JYyeAZCwXd4PzOk8o9TWg3IfDV3H3IW7UePhMBt8nJW/djEr2lCvFkrtVONPbA==","nonce":"1494577237","timestamp":"1566875499"}
        System.out.println("加密后: " + mingwen);

        JSONObject json = JSONObject.parseObject(mingwen);
        //解密
        String resp = secretUtil.decryptMsg(json.getString("signature"), timestamp, nonce, json.getString("encrypt"));
        System.out.println("解密后: " + resp);
    }
}
