package com.demo.springboot.web.service;

import org.springframework.stereotype.Service;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 可用于接口重试的接口
 * @date 2020/12/28 16:12
 * @see
 */
public interface RetryService {

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 银行账户转账接口
     * @date 2020/12/28 16:16
     */
    void retryTransferAccounts(int fromAccountId, int toAccountId, float money) throws Exception;
}
