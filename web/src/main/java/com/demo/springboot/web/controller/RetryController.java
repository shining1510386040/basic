package com.demo.springboot.web.controller;

import com.demo.springboot.web.service.RetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 测试接口重试
 * @date 2020/12/28 16:55
 * @see
 */
@RestController
@RequestMapping("/retry")
public class RetryController {

    @Autowired
    private RetryService retryService;

    @PostMapping("/transfer")
    public void transfer(@RequestParam int fromAccount, @RequestParam int toAccountId, @RequestParam float money) {

        try {
            retryService.retryTransferAccounts(fromAccount, toAccountId, money);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
