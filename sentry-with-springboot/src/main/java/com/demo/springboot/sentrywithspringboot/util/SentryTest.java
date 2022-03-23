package com.demo.springboot.sentrywithspringboot.util;

import io.sentry.Sentry;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 测试java sdk 发送日志到sentry
 * @date 2022/1/25 19:25
 * @see
 */
public class SentryTest {

    public static void main(String[] args) {
        // 初始化
        Sentry.init("http://13e4315a3e0f44b2b41e3623441ae6bd@127.0.0.1:9000/7");

        // 捕获异常

        try {
            throw new Exception("这是一个异常消息2");
        } catch (Exception e) {
            // sentry 5.5.3
            Sentry.captureException(e);
        }

        Sentry.captureMessage("哈哈哈");


    }

}
