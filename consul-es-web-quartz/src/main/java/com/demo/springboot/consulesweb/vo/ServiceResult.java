package com.demo.springboot.consulesweb.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 服务响应结果
 * @date 2021/1/29 11:25
 * @see
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResult implements Serializable {

    /**
     * 响应码
     */
    private String returnCode;
    /**
     * 响应信息
     */
    private String returnMessage;

    /**
     * 响应数据
     */
    private Object data;

}
