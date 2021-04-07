package com.demo.springboot.zipkinservice.vo;

import java.io.Serializable;

/**
 * @param
 * @author Wenyi Cao
 * @version 1.0
 * @description 服务返回结果
 * @date 2021/2/25 11:13
 * @return
 */
public class ServiceResult implements Serializable {

    private static final long serialVersionUID = 1L;

    public ServiceResult(String code, String message) {
        this.returnCode = code;
        this.returnInfo = message;
    }

    public ServiceResult() {
        this.returnCode = "111-111";
        this.returnInfo = "";
        this.requestId = "000000";
    }

    /**
     * 服务返回数据
     */
    private Object data;
    /**
     * 请求ID
     */
    private String requestId;
    /**
     * 编码规则，执行成功返回000-000，执行错误：前三位是功能定义，后三位错误代码，例如 005-001 报表查询报错
     */
    private String returnCode;
    /**
     * 服务执行状态码描述，可显示在前端页面
     */
    private String returnInfo;
    /**
     * 服务执行结果成功/失败
     */
    private Boolean result = true;
    /**
     * 服务执行结果失败的异常信息
     */
    private String stackTrace;
    private String errorSQL;

    public String getErrorSQL() {
        return errorSQL;
    }

    public void setErrorSQL(String errorSQL) {
        this.errorSQL = errorSQL;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getReturnInfo() {
        return returnInfo;
    }

    public void setReturnInfo(String returnInfo) {
        this.returnInfo = returnInfo;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Object getData() {
        return data;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

}
