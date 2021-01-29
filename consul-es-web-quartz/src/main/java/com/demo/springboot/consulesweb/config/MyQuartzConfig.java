package com.demo.springboot.consulesweb.config;

import com.demo.springboot.consulesweb.service.quartz.MyJobService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description quartz 定时任务配置类,可以不用此配置bean，使用工具类添加job到调度器即可
 * QuartzAutoConfiguration：自动配置
 * @date 2021/1/29 16:13
 * @see
 */
@Configuration
public class MyQuartzConfig {


    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 定时任务task详情
     * @date 2021/1/29 16:14
     */
    @Bean("jobDetail")
    public JobDetail jobDetail() {
        JobDetail ret = JobBuilder
                // 任务实现类
                .newJob(MyJobService.class)
                // 标识
                .withIdentity("myJobServiceDetail")
                .storeDurably()
                .build();
        return ret;
    }

    /**
     * @param jobDetail 具体执行的定时任务
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 触发器，每隔一段时间触发 定时任务；
     * @date 2021/1/29 16:23
     */
    @Bean
    public Trigger trigger(@Qualifier("jobDetail") JobDetail jobDetail) {

        ScheduleBuilder scheduleBuilder = SimpleScheduleBuilder
                .simpleSchedule()
                // 每隔3秒周期性执行
                .withIntervalInSeconds(3)
                .repeatForever();
        TriggerBuilder triggerBuilder = TriggerBuilder
                .newTrigger()
                // 任务详情
                .forJob(jobDetail)
                // 触发器标识
                .withIdentity("myJobTrigger")
                .withSchedule(scheduleBuilder);
        return triggerBuilder.build();
    }

}
