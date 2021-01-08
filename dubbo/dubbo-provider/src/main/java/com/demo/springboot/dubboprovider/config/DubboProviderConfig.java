//package com.demo.springboot.dubboprovider.config;
//
//import com.demo.springboot.dubboserviceapi.service.OrderService;
//import org.apache.dubbo.config.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author Wenyi Cao
// * @version 1.0
// * @link
// * @description 暴露服务方式2：基于配置bean
// * 服务提供方dubbo配置：provider.xml ---> java
// * @date 2021/1/8 14:56
// * @see
// */
//@Configuration
//public class DubboProviderConfig {
//
//    //     <dubbo:application name="demo-provider"/>
//
//
//    /**
//     * 应用配置
//     */
//    @Bean
//    public ApplicationConfig applicationConfig() {
//        ApplicationConfig applicationConfig = new ApplicationConfig();
//        applicationConfig.setName("dubbo-service-provider");
//        return applicationConfig;
//    }
//
//    //    <dubbo:registry address="zookeeper://127.0.0.1:2181"/>
//
//    /**
//     * 服务注册中心配置：
//     */
//    @Bean
//    public RegistryConfig registryConfig() {
//        RegistryConfig registryConfig = new RegistryConfig();
////        registryConfig.setProtocol("zookeeper");
////        registryConfig.setAddress("127.0.0.1:2181");
//        registryConfig.setProtocol("nacos");
//        registryConfig.setAddress("127.0.0.1:8848");
//        return registryConfig;
//    }
////    <dubbo:protocol name="dubbo" port="20890"/>
////    <bean id="orderService" class="com.demo.springboot.dubboprovider.service.OrderServiceImpl"/>
////    <dubbo:service interface="com.demo.springboot.dubboserviceapi.service.OrderService" ref="orderService"/>
//
//    /**
//     * 传输协议配置
//     */
//    @Bean
//    public ProtocolConfig protocolConfig() {
//        ProtocolConfig protocolConfig = new ProtocolConfig();
//        protocolConfig.setName("dubbo");
//        protocolConfig.setPort(20890);
//        return protocolConfig;
//    }
//
//    @Bean
//    public ServiceConfig<OrderService> serviceConfig(OrderService ref) {
//
//        ServiceConfig<OrderService> serviceConfig = new ServiceConfig<>();
//        serviceConfig.setInterface(OrderService.class);
//        serviceConfig.setRef(ref);
//        serviceConfig.setVersion("1.0.0");
//
//        // 接口的方法 配置
//        MethodConfig methodConfig = new MethodConfig();
//        methodConfig.setName("getOrderDetail");
//        methodConfig.setTimeout(1000);
//
//        // 关联
//        List<MethodConfig> methodConfigList = new ArrayList<>();
//        methodConfigList.add(methodConfig);
//        serviceConfig.setMethods(methodConfigList);
//
//        return serviceConfig;
//    }
//
//}
