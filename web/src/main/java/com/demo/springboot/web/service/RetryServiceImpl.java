package com.demo.springboot.web.service;

import com.demo.springboot.web.vo.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 测试接口重试
 * @date 2020/12/28 16:17
 * @see
 */
@Service
public class RetryServiceImpl implements RetryService {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RestTemplate restTemplate;

    private final String TRANSFER_URL = "http://localhost:8088/bank/transfer";

    private AtomicInteger retry = new AtomicInteger(0);

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 模拟转账操作
     * Retryable：遇到异常 重试3次
     * @date 2020/12/28 16:29
     */
    @Transactional
    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 3000, multiplier = 1, maxDelay = 10000))
    @Override
    public void retryTransferAccounts(int fromAccountId, int toAccountId, float money) throws Exception {

        //1.调用远程接口 账户1 扣款
//        Map<String, Object> paramMap = new HashMap<>(8);
//        paramMap.put("bankCardNum", fromAccountId);
//        paramMap.put("money", money);
//        ServiceResult serviceResult = restTemplate.postForObject(TRANSFER_URL, paramMap, ServiceResult.class);
//        String returnCode = serviceResult.getReturnCode();
//        System.out.println("==============================>>>>>> returnCode:" + returnCode);
        //2.本地调用  账户2 增加金额
        String sql = "update bank_account t set t.money = t.money + ? where t.bank_card_num = ?";
        Object[] args = new Object[]{
                money,
                toAccountId
        };
        System.out.println("=========================》》服务调用了：" + retry.incrementAndGet() + "次。。");
//        int num = 1 / 0;
        int update = jdbcTemplate.update(sql, args);
    }

    @Recover
    public void recover(Exception e) {
        e.printStackTrace();
        System.out.println("===========>>>> 回调方法执行。");
    }
}
