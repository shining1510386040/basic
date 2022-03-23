package com.demo.springboot.mybatiswithspringboot.service;

import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 同步服务处理类
 * @date 2022/3/22 20:51
 * @see
 */
@Service
public class SyncService {

    private Random random = new Random();

    public String service1() {
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

    public String service2() {
        System.out.println("开始做任务2");
        System.out.println("当前线程为：" + Thread.currentThread().getName());
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(random.nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("完成任务2，耗时：" + (end - start) + "毫秒");
        return "ok";
    }

    public String service3() {
        System.out.println("开始做任务3");
        System.out.println("当前线程为：" + Thread.currentThread().getName());
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(random.nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("完成任务3，耗时：" + (end - start) + "毫秒");
        return "ok";
    }
}
