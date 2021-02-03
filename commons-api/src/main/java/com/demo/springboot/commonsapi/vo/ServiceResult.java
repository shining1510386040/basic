package com.demo.springboot.commonsapi.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 服务响应结果
 * @date 2021/2/3 19:12
 * @see
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResult implements Serializable {

    private String returnCode;
    private String returnInfo;
    private Object data;
}
