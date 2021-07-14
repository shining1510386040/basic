package com.demo.springboot.dubboserviceapi.exception;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 自定义异常
 * @date 2021/7/1 16:54
 * @see
 */
public class CustomDubboException extends RuntimeException {

    private String code;
    private String message;

    public CustomDubboException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
