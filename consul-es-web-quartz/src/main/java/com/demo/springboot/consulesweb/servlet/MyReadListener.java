package com.demo.springboot.consulesweb.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description servlet3.0 非阻塞IO-基于 事件监听机制
 * @date 2021/5/12 18:16
 * @see
 */
public class MyReadListener implements ReadListener {

    private ServletInputStream inputStream;
    private AsyncContext asyncContext;

    public MyReadListener(ServletInputStream input, AsyncContext context) {
        this.inputStream = input;
        this.asyncContext = context;
    }

    //数据可用时触发执行
    @Override
    public void onDataAvailable() throws IOException {
        System.out.println("数据可用时触发执行");
    }

    //数据读完时触发调用
    @Override
    public void onAllDataRead() throws IOException {
        try {
            Thread.sleep(3000);//暂停5秒，模拟耗时处理数据
            PrintWriter out = asyncContext.getResponse().getWriter();
            out.write("数据读完了");
            out.flush();
            System.out.println("数据读完了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //数据出错触发调用
    @Override
    public void onError(Throwable t) {
        System.out.println("数据 出错");
        t.printStackTrace();
    }
}
