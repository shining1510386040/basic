package com.demo.springboot.consulesweb.utils;

import com.mongodb.client.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.security.PrivateKey;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 操作mongo工具类
 * @date 2021/1/28 17:06
 * @see
 */
@Component
public class MongoClientUtil {

    @Autowired
    private MongoClient mongoClient;

    @Autowired
    private MongoTemplate mongoTemplate;

    public void set(){
        // todo ..
//        mongoClient.getDatabase("").listCollections();

//        mongoTemplate.createCollection()
    }
}
