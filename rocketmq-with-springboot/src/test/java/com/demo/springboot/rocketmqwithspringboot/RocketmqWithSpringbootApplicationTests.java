package com.demo.springboot.rocketmqwithspringboot;

import com.demo.springboot.rocketmqwithspringboot.producer.DemoProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RocketmqWithSpringbootApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private DemoProducer demoProducer;

    @Test
    public void testsend(){
//        Caused by: org.apache.rocketmq.remoting.exception.RemotingConnectException: connect to <172.18.62.112 :10911> failed
        demoProducer.produce();
    }

}
