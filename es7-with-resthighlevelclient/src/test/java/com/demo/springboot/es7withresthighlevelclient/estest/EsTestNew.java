package com.demo.springboot.es7withresthighlevelclient.estest;

import com.demo.springboot.es7withresthighlevelclient.entity.Lol;
import com.demo.springboot.es7withresthighlevelclient.entity.Student;
import com.demo.springboot.es7withresthighlevelclient.service.LolService;
import com.demo.springboot.es7withresthighlevelclient.service.StudentElasticSearchService;
import com.demo.springboot.es7withresthighlevelclient.service.util.ElasticSearchResponseEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description es7与springboot 整合测试类
 * @date 2021/4/7 17:27
 * @see
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class EsTestNew {

    public static final String INDEX_NAME = "student_index";

    @Autowired
    private StudentElasticSearchService studentElasticSearchService;

    /**
     * 插入索引库
     */
    @Test
    public void createIndex() throws IOException {
        String mapping = "{\"properties\":{\"name\":{\"type\":\"keyword\"}," +
                "\"date1\":{\"type\":\"date\"}," +
                "\"date2\":{\"type\":\"date\"}," +
                "\"updateTime\":{\"type\":\"date\"}}}\n";
        studentElasticSearchService.createIndex(INDEX_NAME, mapping);
    }

    @Test
    public void insertStu() {
        Student stu = Student.builder().id(123L).birth(new Date()).name("张三").nikename("zhangsan").build();
        stu.setEsId(UUID.randomUUID().toString());
        stu.setEsIndex(INDEX_NAME);
        stu.setEsType("_doc");
        studentElasticSearchService.insert(stu);
    }

    @Test
    public void findById() {
        String id = "97fbe2b5-aa20-478d-949b-8cb0753c7d22";
        Student stu = Student.builder().build();
        stu.setEsId(id);
        stu.setEsIndex(INDEX_NAME);
        String s = studentElasticSearchService.selectById(stu);
        System.out.println(s);
    }

    @Test
    public void selectByMultiConditionAsc() {
        Student stu = Student.builder().name("zhang").build();
        stu.setEsIndex(INDEX_NAME);
        ElasticSearchResponseEntity responseEntity = studentElasticSearchService.selectByMultiConditionAsc(stu, "id", 1, 20);
        System.out.println(responseEntity);
    }


}
