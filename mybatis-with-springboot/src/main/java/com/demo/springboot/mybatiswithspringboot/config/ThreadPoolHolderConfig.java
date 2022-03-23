package com.demo.springboot.mybatiswithspringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 线程池配置类
 * @date 2022/3/23 10:09
 * @see
 */
@Configuration
public class ThreadPoolHolderConfig {

    @Bean("pool1")
    public ExecutorService threadPoolExecutor1() {
        ExecutorService poolExec = new ThreadPoolExecutor(
                // 核心线程数
                5,
                // 最大线程数
                10,
                // 大于核心线程数的线程的最大空闲等待时间
                30,
                // 时间单位
                TimeUnit.SECONDS,
                // 任务的等待队列
                new LinkedBlockingQueue<Runnable>(15),
                // 任务拒绝策略:直接拒绝并抛出拒绝执行异常RejectedExecutionException
                new ThreadPoolExecutor.AbortPolicy()
        );
        return poolExec;
    }

    @Bean("pool2")
    public ExecutorService threadPoolExecutor2() {
        ExecutorService poolExec = new ThreadPoolExecutor(
                // 核心线程数
                5,
                // 最大线程数
                10,
                // 大于核心线程数的线程的最大空闲等待时间
                30,
                // 时间单位
                TimeUnit.SECONDS,
                // 任务的等待队列
                new LinkedBlockingQueue<Runnable>(15),
                // 任务拒绝策略:由主线程去执行，不使用线程池
//                使用此策略，如果添加到线程池失败，那么主线程会自己去执行该任务，
//                不会等待线程池中的线程去执行。就像是个急脾气的人，我等不到别人来做这件事就干脆自己干
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
        return poolExec;
    }

    @Bean("pool3")
    public ExecutorService threadPoolExecutor3() {
        ExecutorService poolExec = new ThreadPoolExecutor(
                // 核心线程数
                5,
                // 最大线程数
                10,
                // 大于核心线程数的线程的最大空闲等待时间
                30,
                // 时间单位
                TimeUnit.SECONDS,
                // 任务的等待队列
                new LinkedBlockingQueue<Runnable>(15),
                // 任务拒绝策略:直接拒绝此任务的执行，不抛出任何异常
                new ThreadPoolExecutor.DiscardPolicy()
        );
        return poolExec;
    }

    @Bean("pool4")
    public ExecutorService threadPoolExecutor4() {
        ExecutorService poolExec = new ThreadPoolExecutor(
                // 核心线程数
                5,
                // 最大线程数
                10,
                // 大于核心线程数的线程的最大空闲等待时间
                30,
                // 时间单位
                TimeUnit.SECONDS,
                // 任务的等待队列
                new LinkedBlockingQueue<Runnable>(15),
                // 任务拒绝策略:抛弃任务队列中最老的任务，当前任务入队
//                丢弃最老的。也就是说如果队列满了，会将最早进入队列的任务删掉腾出空间，再尝试加入队列。
//                因为队列是队尾进，队头出，所以队头元素是最老的，因此每次都是移除对头元素后再尝试入队
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );
        return poolExec;
    }
}
