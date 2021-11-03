package com.demo.springboot.shardingspherewithspringboot.common;

/**
 * @ClassName BaseException
 * @Author shancl
 *
 * @Description 自定义基本异常
 * - 基本异常满意项目中的所有需求
 * - 如果存在特殊场景需要获取指定异常，或明确异常类型可集成该异常
 *
 * @createTime 2020年12月01日 17时57分
 */
public class BaseException extends RuntimeException{

    private String message;

    public BaseException(){
        super();
    }

    public BaseException(String message){
        super(message);
        this.message = message;
    }

    public BaseException(String message, Throwable cause){
        super(message, cause);
        this.message = message;
    }

    public BaseException(Throwable cause){
        super(cause);
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
