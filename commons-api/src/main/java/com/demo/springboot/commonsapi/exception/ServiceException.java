package com.demo.springboot.commonsapi.exception;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 服务通用的异常
 * @date 2021/2/3 19:07
 * @see
 */
public class ServiceException extends RuntimeException {

    private String message;

    public ServiceException(String message) {
        super(message);
        this.message = message;
    }
}
