package com.demo.springboot.rocketmqwithspringboot.config;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description rocketmq发送异步消息：异步回调监听
 * @date 2021/12/10 16:16
 * @see
 */
@Slf4j
public class SendCallbackListener implements SendCallback {

    private int id;

    public SendCallbackListener(int id) {
        this.id = id;
    }

    @Override
    public void onSuccess(SendResult sendResult) {
        log.info("CallBackListener on success : " + JSONObject.toJSONString(sendResult));
    }

    @Override
    public void onException(Throwable throwable) {
        log.error("CallBackListener on exception : ", throwable);
    }
}

