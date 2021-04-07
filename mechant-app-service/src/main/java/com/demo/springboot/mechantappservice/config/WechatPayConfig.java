package com.demo.springboot.mechantappservice.config;

import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sun.security.rsa.RSAPrivateCrtKeyImpl;
import sun.security.x509.X509CertImpl;

import java.io.ByteArrayInputStream;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 微信支付配置
 * @date 2021/3/16 11:22
 * @see
 */
@Configuration
public class WechatPayConfig {

    /**
     * 商户id
     */
    @Value("${wx.merchantId}")
    private String merchantId;

    /**
     * 商户序列号
     */
    @Value("${wx.merchantSerialNumber}")
    private String merchantSerialNumber;

    /**
     * 商户私钥
     */
    @Value("${wx.merchantPrivateKey}")
    private String merchantPrivateKey;

    /**
     * 微信支付认证签名
     */
    @Value("${wx.wechatpayCertificates}")
    private String wechatpayCertificates;

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 获取微信支付封装的httpclient：
     * 得到的HttpClient在执行请求时将自动携带身份认证信息，并检查应答的微信支付签名。
     * @date 2021/3/16 11:24
     */
    @Bean
    public HttpClient getWxHttpClient() {

        PrivateKey privateKey = null;
        try {
            privateKey = RSAPrivateCrtKeyImpl.newKey(merchantPrivateKey.getBytes());
            List<X509Certificate> certificateList = new ArrayList<>(5);
            X509CertImpl x509Cert = new X509CertImpl(wechatpayCertificates.getBytes());
            certificateList.add(x509Cert);
            WechatPayHttpClientBuilder builder = WechatPayHttpClientBuilder.create()
                    .withMerchant(merchantId, merchantSerialNumber, privateKey)
                    // todo ...
                    .withWechatpay(certificateList);
// ... 接下来，你仍然可以通过builder设置各种参数，来配置你的HttpClient

// 通过WechatPayHttpClientBuilder构造的HttpClient，会自动的处理签名和验签
            HttpClient httpClient = builder.build();
            return httpClient;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
