package com.demo.springboot.consulesweb.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description consul config测试
 * @date 2021/1/26 13:57
 * @see
 */
@Component
@ConfigurationProperties(prefix = "student")
@Data
public class StudentProperties {

    private String name;
    private int age;
    private String sex;
}
