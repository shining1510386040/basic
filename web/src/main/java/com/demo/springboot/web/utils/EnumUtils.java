package com.demo.springboot.web.utils;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 枚举工具类
 * @date 2021/1/12 17:31
 * @see
 */
public class EnumUtils {

    /**
     * token过期时间：30秒
     */
    public static final long TOKEN_EXPIRE_TIME = 30;

    /**
     * token枚举
     * token规则：
     * 前缀+年份+uuid 总共20位
     */
    public enum TokenEnum {
        /**
         * 幂等性校验token
         */
        API_DEMPOTENT("apidempotent"),
        /**
         * 登录校验token
         */
        LOGIN_SESSION("login");
        // 前缀
        private String prefix;

        TokenEnum(String prefix) {
            this.prefix = prefix;
        }

        public String getPrefix() {
            return prefix;
        }
    }

    /**
     * 服务响应枚举
     */
    public enum ResponseEnum {

        ILLEGAL_ARGUMENT("500101", "请求参数不符合规范"),
        REPEATED_OPERATION("500102", "重复的操作"),
        SERVER_ERROR("500100", "服务端异常");

        private String code;
        private String message;

        ResponseEnum(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }


}
