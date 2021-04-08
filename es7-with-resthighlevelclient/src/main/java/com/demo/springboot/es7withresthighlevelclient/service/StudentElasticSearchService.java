package com.demo.springboot.es7withresthighlevelclient.service;

import com.demo.springboot.es7withresthighlevelclient.entity.Student;
import com.demo.springboot.es7withresthighlevelclient.service.util.ElasticSearchMapper;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 学生 es index索引库 操作接口
 * @date 2021/4/8 11:34
 * @see
 */
public interface StudentElasticSearchService extends ElasticSearchMapper<Student> {

    // 其他接口 todo

    String sayHi();

}
