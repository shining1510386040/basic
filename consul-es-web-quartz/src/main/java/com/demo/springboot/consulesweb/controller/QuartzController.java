package com.demo.springboot.consulesweb.controller;

import com.demo.springboot.consulesweb.service.quartz.Task1;
import com.demo.springboot.consulesweb.service.quartz.Task2;
import com.demo.springboot.consulesweb.utils.QuartzManagerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 测试quartz定时任务
 * @date 2021/1/29 17:26
 * @see
 */
@RestController
@RequestMapping("/quartz")
public class QuartzController {

    @Autowired
    private QuartzManagerUtil quartzManagerUtil;

    @GetMapping("/add1")
    public String addTask1() {
        String cron = "5 * * * * ? ";
        Task1 task1 = new Task1();
        quartzManagerUtil.addJob("task1-1", task1.getClass(), cron);
        quartzManagerUtil.startJobs();
        return "ok";
    }

    @GetMapping("/add2")
    public String addTask2() {
        // 每5秒执行一次
        String cron = "5 * * * * ? ";
        Task2 task1 = new Task2();
        quartzManagerUtil.addJob("task2-1", task1.getClass(), cron);
        quartzManagerUtil.startJobs();
        return "ok";
    }

    //   每6秒执行一次
    @Scheduled(cron = "6 * * * * ? ")
//    @Scheduled(cron = "0 0 2 * * ?")//凌晨两点执行
    public void springbootSchedule() {
        // ....业务处理
        System.out.println("==============>springboot job is running :");

    }
}
