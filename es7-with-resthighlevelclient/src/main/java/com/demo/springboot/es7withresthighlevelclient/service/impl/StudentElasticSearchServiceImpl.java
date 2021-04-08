package com.demo.springboot.es7withresthighlevelclient.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.demo.springboot.es7withresthighlevelclient.entity.Student;
import com.demo.springboot.es7withresthighlevelclient.service.StudentElasticSearchService;
import com.demo.springboot.es7withresthighlevelclient.service.util.ElasticSearchMapper;
import com.demo.springboot.es7withresthighlevelclient.service.util.ElasticSearchMapperImpl;
import com.demo.springboot.es7withresthighlevelclient.service.util.ElasticSearchResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 测试
 * @date 2021/4/8 11:31
 * @see
 */
@Service
@Slf4j
public class StudentElasticSearchServiceImpl extends ElasticSearchMapperImpl<Student> implements StudentElasticSearchService {

    // use a

    private final String INDEX_NAME = "student_index";

    @Override
    public String sayHi() {
//        restClient.
        return null;
    }

    //    public boolean isExists(String id) {
//        Student student = new Student(INDEX_NAME);
//        student.setEsId(id);
//        return super.isExists(student);
//    }
//
//    @Override
//    public String insert(Student entry) {
//        entry.setEsIndex(INDEX_NAME);
//        entry.setEsId(entry.getId() + "");
//        return super.insert(entry);
//    }
//
//    public Student selectById(String id) {
//        Student student = new Student(INDEX_NAME);
//        student.setEsId(id);
//        String jsonString = super.selectById(student);
//        return StringUtils.isEmpty(jsonString) ? null : JSONObject.parseObject(jsonString, Student.class);
//    }
//
//    @Override
//    public String update(Student entry) {
//        entry.setEsIndex(INDEX_NAME);
//        entry.setEsId(entry.getId() + "");
//        return super.update(entry);
//    }
//
//    public String delete(String id) {
//        Student student = new Student(INDEX_NAME);
//        student.setEsId(id);
//        return super.delete(student);
//    }
//
//    public ElasticSearchResponseEntity selectByMultiCondition(Student entry, Integer page, Integer length) {
//        try {
//            entry.setEsIndex(INDEX_NAME);
//            return super.selectByMultiConditionDesc(entry, "birth", page, length);
//        } catch (Exception e) {
//            log.error("出错！", e);
//        }
//        return null;
//    }
}
