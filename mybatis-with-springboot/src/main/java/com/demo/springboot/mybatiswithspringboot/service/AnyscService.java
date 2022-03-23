package com.demo.springboot.mybatiswithspringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 异步服务处理
 * @date 2022/3/22 20:40
 * @see
 */
@Service
public class AnyscService {

    private Random random = new Random();

    @Autowired
    @Qualifier("pool1")
    private ExecutorService poolExecutor;

    @Async
    public Future<String> process1() {
        System.out.println("开始做任务一");
        System.out.println("当前线程为：" + Thread.currentThread().getName());
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(random.nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("完成任务一，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务一完成");
    }

    @Async
    public Future<String> process2() {
        System.out.println("开始做任务二");
        System.out.println("当前线程为：" + Thread.currentThread().getName());
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(random.nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("完成任务二，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务二完成");
    }

    @Async
    public Future<String> process3() {
        System.out.println("开始做任务三");
        System.out.println("当前线程为：" + Thread.currentThread().getName());
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(random.nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("完成任务三，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务三完成");
    }


    public Future<String> processWithCustomThread1() {

        Future<String> future = poolExecutor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("开始做任务一");
                System.out.println("当前线程为：" + Thread.currentThread().getName());
                long start = System.currentTimeMillis();
                try {
                    Thread.sleep(random.nextInt(10000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                long end = System.currentTimeMillis();
                System.out.println("完成任务一，耗时：" + (end - start) + "毫秒");
                return "ok";
            }
        });

        return future;
    }

    public Future<String> processWithCustomThread2() {

        Future<String> future = poolExecutor.submit(() -> {
            System.out.println("开始做任务二");
            System.out.println("当前线程为：" + Thread.currentThread().getName());
            long start = System.currentTimeMillis();
            try {
                Thread.sleep(random.nextInt(10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long end = System.currentTimeMillis();
            System.out.println("完成任务二，耗时：" + (end - start) + "毫秒");
            return "ok";
        });

        return future;
    }


}
