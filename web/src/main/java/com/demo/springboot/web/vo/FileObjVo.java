package com.demo.springboot.web.vo;

import lombok.Data;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description oss正在操作的文件
 * @date 2021/1/18 10:45
 * @see
 */
@Data
public class FileObjVo {

    /**
     * 文件唯一标识
     */
    private String uid;

    /**
     * 文件名
     */
    private String name;

    /**
     * 处理状态：uploading、done、error、removed
     */
    private String status;

    /**
     * 服务端响应内容
     */
    private Object response;

    /**
     * 下载链接额外属性
     */
    private Object linkProps;
}
