package com.demo.springboot.consulesweb.repository.es;

import com.demo.springboot.consulesweb.entity.es.Person;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 人员repository:响应式编程Mono Flux
 * @date 2021/1/26 18:59
 * @see ReactiveCrudRepository
 */
@Repository
public interface PersonRepository extends ReactiveElasticsearchRepository<Person, String> {

}
