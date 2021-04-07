package com.demo.springboot.consulesweb.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description hbase 响应结果
 * @date 2021/2/24 15:42
 * @see
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    private String message;

    private String code;

    private Object data;

}
