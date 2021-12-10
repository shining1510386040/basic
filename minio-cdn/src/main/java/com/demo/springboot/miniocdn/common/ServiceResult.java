package com.demo.springboot.miniocdn.common;

import org.slf4j.MDC;

import java.io.Serializable;

/**
 * @param
 * @author Wenyi Cao
 * @version 1.0
 * @description 服务的返回结果
 * @date 2020/12/23 14:16
 * @return
 */
public class ServiceResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String exampleData = "xxxxxx";

    public ServiceResult(String code, String message) {
        this.returnCode = code;
        this.returnInfo = message;
    }

    public ServiceResult() {
        this.returnCode = "111-111";
        this.returnInfo = "";
        this.requestId = "000000";
    }

    private Object data;
    private String requestId;
    /**
     * 编码规则，执行成功返回000-000，执行错误：前三位是功能定义，后三位错误代码，例如 005-001 报表查询报错
     */
    private String returnCode;
    private String returnInfo;
    private String customInfo;
    private Boolean result = true;
    private String stackTrace;
    private String errorSQL;

    public static ServiceResult success(String language, Object data) {
        return new ServiceResult(true, language, ResultEnum.SUCCESS, null, data);
    }

    public static ServiceResult error(String language, ResultEnum resultEnum, String customInfo) {
        return new ServiceResult(false, language, resultEnum, customInfo, null);
    }

    private ServiceResult(Boolean result, String language, ResultEnum resultEnum, String customInfo, Object data) {
        this.result = result;
        this.returnCode = resultEnum.getCode();
        this.returnInfo = resultEnum.getMessage(language);
        this.customInfo = customInfo;
        this.data = data;
        this.requestId = MDC.get(LogInterceptor.REQUEST_ID);
    }


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

    public void setCustomInfo(String customInfo) {
        this.customInfo = customInfo;
    }

    public String getCustomInfo() {
        return customInfo;
    }
}
