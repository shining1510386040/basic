package com.demo.springboot.web.controller;

import com.demo.springboot.web.cacheTraditional.AccountService;
import com.demo.springboot.web.entity.Account;
import com.demo.springboot.web.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 查询服务controller
 * @date 2020/12/28 13:26
 * @see
 */
@RestController
@RequestMapping("/query")
public class QueryController {

    @Autowired
    private QueryService queryService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/getAccount")
    public List<Map<String, Object>> getAccount(@RequestParam String username) {
        List<Map<String, Object>> accountByUserName = queryService.getAccountByUserName(username);
        return accountByUserName;
    }

    @GetMapping("/getAccountTra")
    public List<Map<String, Object>> getAccountTraditional(@RequestParam String username) {
        List<Map<String, Object>> accountByUserName = accountService.getAccoutByName(username);
        return accountByUserName;
    }

    @GetMapping("/getAccountTra/reload")
    public void evictCacheByKey(String username){
        accountService.reload(username);
    }
}
