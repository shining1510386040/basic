package com.demo.springboot.miniocdn.common;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLTimeoutException;

/**
 * @ClassName GlobalExceptionHandler
 * @Author shancl
 * @Description 全局异常统一处理
 * -
 * @createTime 2020年12月01日 18时06分
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Resource
    private HttpServletRequest request;


    /**
     * 处理自定义异常
     *
     * @param baseException 拦截到的自定义异常
     * @return CommonResult
     */
    @ExceptionHandler(BaseException.class)
    public ServiceResult baseExceptionHandler(BaseException baseException) {
        log.error(baseException.getMessage(), baseException);
        baseException.printStackTrace();
        ServiceResult responseInfo = new ServiceResult();
        responseInfo.setResult(Boolean.FALSE);
        responseInfo.setReturnCode(ResultEnum.RUN_TIME_EXCEPTION.getCode());
        responseInfo.setReturnInfo(ResultEnum.RUN_TIME_EXCEPTION.getMessage(request.getLocale().getLanguage()));
        responseInfo.setCustomInfo(baseException.getMessage());
        responseInfo.setRequestId(MDC.get(LogInterceptor.REQUEST_ID));

        return responseInfo;
    }

    /**
     * 处理其它异常
     *
     * @param exception 拦截到的未知异常
     * @return CommonResult
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ServiceResult exceptionHandler(Exception exception) {
        log.error(exception.getMessage(), exception);
        exception.printStackTrace();

        ServiceResult responseInfo = new ServiceResult();
        responseInfo.setResult(Boolean.FALSE);
        responseInfo.setReturnCode(ResultEnum.RUN_TIME_EXCEPTION.getCode());
        responseInfo.setReturnInfo(ResultEnum.RUN_TIME_EXCEPTION.getMessage(request.getLocale().getLanguage()));
        responseInfo.setRequestId(MDC.get(LogInterceptor.REQUEST_ID));

        return responseInfo;
    }


    /**
     * 处理断言异常
     *
     * @param illegalArgumentException 拦截到的断言异常
     * @return CommonResult
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ServiceResult illegalArgumentException(IllegalArgumentException illegalArgumentException) {
        log.error(illegalArgumentException.getMessage(), illegalArgumentException);
        illegalArgumentException.printStackTrace();

        ServiceResult responseInfo = new ServiceResult();
        responseInfo.setResult(Boolean.FALSE);
        responseInfo.setReturnCode(ResultEnum.INVALID_PARAM_EXCEPTION.getCode());
        responseInfo.setReturnInfo(ResultEnum.INVALID_PARAM_EXCEPTION.getMessage(request.getLocale().getLanguage()));
        responseInfo.setCustomInfo(illegalArgumentException.getMessage());
        responseInfo.setRequestId(MDC.get(LogInterceptor.REQUEST_ID));

        return responseInfo;
    }

    /**
     * 处理sql超时异常
     *
     * @param exception 拦截到的未知异常
     * @return CommonResult
     */
    @ExceptionHandler(SQLTimeoutException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ServiceResult sqlExceptionHandler(Exception exception) {
        log.error("拦截到SQL超时异常, 异常信息为: " + exception);
        exception.printStackTrace();

        ServiceResult responseInfo = new ServiceResult();
        responseInfo.setResult(Boolean.FALSE);
        responseInfo.setReturnCode(ResultEnum.SQL_TIMEOUT_EXCEPTION.getCode());
        responseInfo.setReturnInfo(ResultEnum.SQL_TIMEOUT_EXCEPTION.getMessage(request.getLocale().getLanguage()));
        responseInfo.setCustomInfo(exception.getMessage());
        responseInfo.setRequestId(MDC.get(LogInterceptor.REQUEST_ID));

        return responseInfo;
    }


}
