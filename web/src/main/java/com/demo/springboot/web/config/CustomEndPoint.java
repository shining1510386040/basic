package com.demo.springboot.web.config;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 自定义endpoint端点
 * @date 2021/2/8 10:55
 * @see
 */
@WebEndpoint(id = "custom")
public class CustomEndPoint {

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description get请求
     * @date 2021/2/8 10:57
     */
    @ReadOperation
    public String endpointCustomRead(String content) {
        return "你请求的内容: " + content;
    }

    @WriteOperation
    public String endpointCustomWrite(String content) {
        return "你写的内容: " + content;
    }

    @DeleteOperation
    public String endpointCustomDelete(String content) {
        return "你删除的内容: " + content;
    }
}
