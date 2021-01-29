package com.demo.springboot.consulesweb.service.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 定时任务1
 * @date 2021/1/29 17:27
 * @see
 */
//@Service
public class Task1 extends QuartzJobBean {


    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        logger.warn("=================>>my task is runing:" + jobExecutionContext.getJobDetail().getJobClass());
    }
}
