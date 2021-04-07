package com.demo.springboot.consulesweb.utils;


/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description hbase result结果生成工具类
 * @date 2021/2/24 15:43
 * @see
 */
public class ResultGenerator {


    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 成功返回
     * @date 2021/2/24 15:45
     */
    public static Result genSuccessResult() {
        return new Result("success", "200", null);
    }

    public static Result genFailResult(String s) {
        return new Result("fail:" + s, "500", null);
    }
}
