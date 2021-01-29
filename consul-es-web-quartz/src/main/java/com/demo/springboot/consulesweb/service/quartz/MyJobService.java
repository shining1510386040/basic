package com.demo.springboot.consulesweb.service.quartz;

import com.demo.springboot.consulesweb.entity.jpa.ProductJpa;
import com.demo.springboot.consulesweb.service.ProductService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description quartz 定时任务服务
 * @date 2021/1/29 16:18
 * @see
 */
@Service
public class MyJobService extends QuartzJobBean {

    @Autowired
    private ProductService productService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 具体定时任务操作
     * @date 2021/1/29 16:19
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        logger.warn("================>>>job执行中：" + jobExecutionContext.getJobDetail().getJobClass().getName());
//        ProductJpa one = productService.getOne(1L);
//        System.out.println(one);

    }
}
