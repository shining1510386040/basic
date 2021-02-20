package com.demo.springboot.dbconnector.config;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.UserTransaction;
import java.util.Properties;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 事务管理器配置
 * @date 2021/2/19 19:35
 * @see
 */
@Configuration
@ComponentScan
@EnableTransactionManagement
public class TransactionManagerConfig {


    @Bean(name = "userTransaction")
    public UserTransaction userTransaction() throws Throwable {
        UserTransactionImp userTransactionImp = new UserTransactionImp();
        userTransactionImp.setTransactionTimeout(10000);
        return userTransactionImp;
    }

    @Bean(name = "atomikosTransactionManager", initMethod = "init", destroyMethod = "close")
    public UserTransactionManager atomikosTransactionManager() throws Throwable {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setForceShutdown(false);
        return userTransactionManager;
    }

    /**
     * @author Wenyi Cao
     * @version 1.0
     * @description 配置jta事务管理器
     * @date 2021/2/20 9:43
     * @param
     * @return
     */
    @Bean(name = "transactionManager")
    @DependsOn({"userTransaction", "atomikosTransactionManager"})
    public JtaTransactionManager transactionManager() throws Throwable {
        UserTransaction userTransaction = userTransaction();
        JtaTransactionManager manager = new JtaTransactionManager(userTransaction, atomikosTransactionManager());
        return manager;
    }

//    @Bean(name = "transactionInterceptor")
//    public TransactionInterceptor transactionInterceptor(PlatformTransactionManager platformTransactionManager) {
//        TransactionInterceptor transactionInterceptor = new TransactionInterceptor();
//        // 事物管理器
//        transactionInterceptor.setTransactionManager(platformTransactionManager);
//        Properties transactionAttributes = new Properties();
//        // test
//        transactionAttributes.setProperty("test*", "PROPAGATION_REQUIRED,-Throwable");
//        // 新增
//        transactionAttributes.setProperty("insert*", "PROPAGATION_REQUIRED,-Throwable");
//        // 修改
//        transactionAttributes.setProperty("update*", "PROPAGATION_REQUIRED,-Throwable");
//        // 删除
//        transactionAttributes.setProperty("delete*", "PROPAGATION_REQUIRED,-Throwable");
//        // 查询
//        transactionAttributes.setProperty("select*", "PROPAGATION_REQUIRED,-Throwable,readOnly");
//        transactionInterceptor.setTransactionAttributes(transactionAttributes);
//        return transactionInterceptor;
//
//    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 设置 代理到ServiceImpl 的Bean：aop的代理模式
     * @date 2021/2/19 19:39
     */
    @Bean
    public BeanNameAutoProxyCreator transactionAutoProxy() {
        BeanNameAutoProxyCreator transactionAutoProxy = new BeanNameAutoProxyCreator();
        transactionAutoProxy.setProxyTargetClass(true);
        // 要代理的bean匹配
        transactionAutoProxy.setBeanNames("*ServiceImpl");
        // 代理事务拦截
        transactionAutoProxy.setInterceptorNames("transactionInterceptor");
        return transactionAutoProxy;
    }
}
