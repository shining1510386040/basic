package com.demo.springboot.apigatewaysubapiservice.controller;

import com.demo.springboot.commonsapi.entity.Order;
import com.demo.springboot.commonsapi.service.OrderService;
import com.demo.springboot.commonsapi.vo.ServiceResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description openFeign 测试接口
 * @date 2021/2/3 18:32
 * @see
 */
@RestController
@RequestMapping("/openfeign")
@Slf4j
public class OpenFeignApiController implements OrderService {

    @Value("${server.port}")
    private int port;

    @GetMapping("/test")
    public String service(@RequestParam String name) {

        return "这里是下游服务》》api-service：port：" + port + "name:" + name;
    }

    @Override
    public ServiceResult create(Order order) {
        // ....
        log.info("=====>>>>这里是下游服务》》api-service：port：" + port);
        return new ServiceResult("200", "创建订单成功", null);
    }
}
