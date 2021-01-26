package com.demo.springboot.consulesweb.controller;

import com.demo.springboot.consulesweb.entity.es.Person;
import com.demo.springboot.consulesweb.repository.es.PersonRepository;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

//    private WebClient webClient;

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
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Person> add(@RequestBody Person person) {
        return personRepository.save(person);
    }

//    @GetMapping("/insertWith")
//    public String insertOne() {
//        System.out.println("添加第一条数据");
//        Mono<Person> mono = webClient
//                .post()
//                .uri("http://localhost:8080/people")
//                //使用2个参数的构造器
//                .body(Mono.just(new Person("wyf", 35)), Person.class)
//                .retrieve()
//                .bodyToMono(Person.class);
//        //使用block()方法订阅Mono并将其变为同步
//        Person person = mono.block();
//        System.out.println(person);
//        //使用block()方法订阅Mono并将其变为同步
//        return person.getId();
//    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 按id获取
     * @date 2021/1/26 19:07
     */
    @GetMapping("/get/{id}")
    public Mono<Person> getById(@PathVariable String id) {
        return personRepository.findById(id);
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 查看所有
     * @date 2021/1/26 19:07
     */
    @GetMapping
    public Flux<Person> list() {
        return personRepository.findAll();
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 按id删除
     * @date 2021/1/26 19:08
     */
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable("id") String id) {
        return personRepository.deleteById(id);
    }
}
