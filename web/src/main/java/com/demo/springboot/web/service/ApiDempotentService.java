package com.demo.springboot.web.service;

import com.demo.springboot.web.vo.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 服务接口（测试幂等服务）
 * @date 2021/1/12 19:09
 * @see
 */
@Service
public class ApiDempotentService {


    public String process() {
        // ...do some work,,,,
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {

            e.printStackTrace();
        }

        return "ok...";
    }
}
