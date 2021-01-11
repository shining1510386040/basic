package com.demo.springboot.web.vo;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 服务响应枚举
 * @date 2021/1/11 10:38
 * @see
 */
public class ServiceResultEnum {

    /**
     * 请求限流枚举
     */
    public enum RequestLimit {
        REQUEST_LIMIT(500, "当前接口请求已达上限");
        private int code;
        private String message;

        RequestLimit(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }


}
