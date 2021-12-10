package com.demo.springboot.rocketmqwithspringboot.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 异步事务消息监听回调
 * @date 2021/12/10 16:18
 * @see
 */
@Slf4j
@RocketMQTransactionListener
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TransactionListener implements RocketMQLocalTransactionListener {

    private static final Map<String, RocketMQLocalTransactionState> TRANSACTION_STATE_MAP = new HashMap<>();

    /**
     * 处理本地事务
     *
     * @param message
     * @param o
     * @return
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        log.info("执行本地事务");
        MessageHeaders headers = message.getHeaders();
        //获取事务ID
        String transactionId = (String) headers.get(RocketMQHeaders.TRANSACTION_ID);
        TRANSACTION_STATE_MAP.put(transactionId, RocketMQLocalTransactionState.UNKNOWN);
        log.info("transactionId is {}", transactionId);
        try {
            Thread.sleep(10 * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        RocketMQLocalTransactionState state = RocketMQLocalTransactionState.ROLLBACK;
        if (Integer.parseInt(transactionId) % 2 == 0) {
            //执行成功，可以提交事务
            state = RocketMQLocalTransactionState.COMMIT;
        }
        log.info("transactionId is {}, state {}", transactionId, state.toString());
        TRANSACTION_STATE_MAP.remove(transactionId);
        return state;
    }

    /**
     * 校验事务状态
     *
     * @param message
     * @return
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        MessageHeaders headers = message.getHeaders();
        //获取事务ID
        String transactionId = (String) headers.get(RocketMQHeaders.TRANSACTION_ID);
        log.info("检查本地事务,事务ID:{}", transactionId);
        RocketMQLocalTransactionState state = TRANSACTION_STATE_MAP.get(transactionId);
        if (null != state) {
            return state;
        }
        return RocketMQLocalTransactionState.ROLLBACK;

    }
}
