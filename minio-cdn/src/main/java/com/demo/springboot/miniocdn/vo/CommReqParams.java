package com.demo.springboot.miniocdn.vo;

import lombok.Data;
import lombok.ToString;

import java.util.Map;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 通用请求参数
 * @date 2021/6/1 10:39
 * @see
 */
@Data
@ToString
public class CommReqParams {

    /**
     * 业务无关请求体信息：appVersion、appType、deviceId、accessToken等
     */
    private Map<String, String> head;
    /**
     * 业务相关请求体信息：pageNo、pageSize、condition（map）过滤条件
     */
    private Map<String, Object> body;
}
