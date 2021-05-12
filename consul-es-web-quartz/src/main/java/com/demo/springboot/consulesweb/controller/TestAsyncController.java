package com.demo.springboot.consulesweb.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description spring MVC 的异步：servlet3.0 异步的封装
 * @date 2021/5/12 17:29
 * @see
 */
@RequestMapping("/async")
@RestController
public class TestAsyncController {

    @RequestMapping("/{testUrl}")
    public DeferredResult<ResponseEntity<String>> testProcess(@PathVariable String testUrl) {

        final DeferredResult<ResponseEntity<String>> deferredResult = new DeferredResult<ResponseEntity<String>>();

        // 业务逻辑异步处理,将处理结果 set 到 DeferredResult
        new Thread(new AsyncTask(deferredResult)).start();


        return deferredResult;
    }

    private static class AsyncTask implements Runnable {

        private DeferredResult result;

        private AsyncTask(DeferredResult result) {
            this.result = result;
        }

        @Override
        public void run() {
            //业务逻辑START
            //...
            //业务逻辑END
            result.setResult(result);
        }
    }
}
