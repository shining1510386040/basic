package com.demo.springboot.web.controller;

import com.demo.springboot.web.annotation.ApiIdempotent;
import com.demo.springboot.web.service.ApiDempotentService;
import com.demo.springboot.web.service.TokenService;
import com.demo.springboot.web.vo.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 幂等性接口测试
 * @date 2021/1/12 19:04
 * @see
 */
@RestController
@RequestMapping("/apidemp")
public class ApidempotentController {

    @Autowired
    private ApiDempotentService apiDempotentService;

    /**
     * @param apiToken token
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description
     * @date 2021/1/12 20:38
     */
    @GetMapping("/test")
    @ApiIdempotent
    public ServiceResult process(@RequestHeader String apiToken) {
        String process = apiDempotentService.process();
        System.out.println("===============>>>:幂等接口测试" + process);
        return new ServiceResult();
    }
}
