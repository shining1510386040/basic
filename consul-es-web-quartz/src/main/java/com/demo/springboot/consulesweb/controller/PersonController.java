package com.demo.springboot.consulesweb.controller;

import com.demo.springboot.consulesweb.entity.es.Person;
import com.demo.springboot.consulesweb.repository.es.PersonRepository;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description es响应式controller测试
 * @date 2021/1/26 19:03
 * @see
 */
@RestController
@RequestMapping("/es/person")
public class PersonController {

    private PersonRepository personRepository;

    /**
     * 构造器注入
     */
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 保存到es
     * @date 2021/1/26 19:07
     */
    @PostMapping("/save")
    public Person add(Person person) {
        Mono<Person> save = personRepository.save(person);
        Person block = save.block();
        return block;
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 按id获取
     * @date 2021/1/26 19:07
     */
    @GetMapping("/get/{id}")
    public Person getById(@PathVariable String id) {
        Mono<Person> data = personRepository.findById(id);
        return data.block();
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 查看所有
     * @date 2021/1/26 19:07
     */
    @GetMapping("/list")
    public List<Person> list() {
        Flux<Person> data = personRepository.findAll();

        return data.collectList().block();
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 按id删除
     * @date 2021/1/26 19:08
     */
    @PostMapping("/delete/{id}")
    public void delete(@PathVariable("id") String id) {
        // todo ...为啥删除不了呢？？？
        Mono<Void> data = personRepository.deleteById(id);
    }
}
