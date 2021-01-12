package com.demo.springboot.web.exception;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 服务通用异常
 * @date 2021/1/12 18:34
 * @see
 */
public class ServiceException extends RuntimeException {

    private String message;

    public ServiceException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
