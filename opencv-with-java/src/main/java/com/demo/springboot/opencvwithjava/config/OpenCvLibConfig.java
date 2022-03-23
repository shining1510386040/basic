package com.demo.springboot.opencvwithjava.config;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description TODO
 * @date 2022/1/14 11:29
 * @see
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * opencv动态库配置
 *
 * @author wangjiajin
 * @date 2021/3/2
 * @desc
 */
@Component
@ConfigurationProperties(prefix = "opencv.lib")
public class OpenCvLibConfig {

    @Value("${opencv.lib.path}")
    private String path;

    @PostConstruct
    public void init() {
        // 关键在于加载dll 动态链接库
        System.load(path);
    }
}
