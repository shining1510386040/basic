package com.demo.springboot.mybatiswithspringboot.controller;

import com.demo.springboot.mybatiswithspringboot.service.AnyscService;
import com.demo.springboot.mybatiswithspringboot.service.SyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 测试异步接口
 * @date 2022/3/22 19:07
 * @see
 */
@RestController
@RequestMapping("/test")
public class AnysController {

    /**
     * 程序异步服务处理
     */
    @Autowired
    private AnyscService anyscService;

    /**
     * 程序同步处理
     */
    @Autowired
    private SyncService syncService;

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 异步处理接口，注解方式
     * @date 2022/3/22 20:48
     */
    @GetMapping("doAsync")
    public String doAsyncTask() {
        long start = System.currentTimeMillis();

        Future<String> task1 = anyscService.process1();
        Future<String> task2 = anyscService.process2();
        Future<String> task3 = anyscService.process3();

        while (true) {
            if (task1.isDone() && task2.isDone() && task3.isDone()) {
                // 三个任务都调用完成，退出循环等待
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();

        System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
        return "ok";
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 使用自定义的线程池
     * @date 2022/3/23 10:54
     */
    @GetMapping("doAsyncPool")
    public String doAsyncTaskWithPool() {
        // 此接口存在并发问题：
        long start = System.currentTimeMillis();

        Future<String> task1 = anyscService.processWithCustomThread1();
        Future<String> task2 = anyscService.processWithCustomThread2();

        while (true) {
            if (task1.isDone() && task2.isDone()) {
                // 三个任务都调用完成，退出循环等待
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();

        System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
        return "ok";
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 同步处理接口
     * @date 2022/3/22 20:51
     */
    @GetMapping("/doSync")
    public String doSync() {

        long start = System.currentTimeMillis();


        String service1 = syncService.service1();
        // 阻塞等待service1 执行完毕再向下执行
        String service2 = syncService.service2();
        String service3 = syncService.service3();


        long end = System.currentTimeMillis();

        System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
        return service1 + service2 + service3;

    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 测试tomcat的并发能力
     * @date 2022/3/23 11:26
     */
    @GetMapping("/conReq")
    public String testCurrent() {
        System.out.println("当前线程为：" + Thread.currentThread().getName());
        return "ok";
    }
}
