package com.demo.springboot.rsainterfaceende.controller;

import cn.shuibo.annotation.Decrypt;
import cn.shuibo.annotation.Encrypt;
import cn.shuibo.util.RSAUtil;
import com.demo.springboot.rsainterfaceende.entity.Person;
import org.springframework.web.bind.annotation.*;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 测试controller
 * @date 2022/1/6 17:11
 * @see
 */
@RestController
@RequestMapping("/test")
public class TestController {


    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 测试对返回体进行加密
     * @date 2022/1/6 17:22
     */
    @Encrypt
    @GetMapping("/en")
    public Person encryption() {
        Person person = new Person();
        person.setAge(24);
        person.setId("1342");
        person.setUsername("zhangsan");
        return person;
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 测试对请求体进行rsa解密
     * @date 2022/1/6 17:24
     */
    @Decrypt
    @PostMapping("/de")
    public String Decryption(@RequestBody Person person) {
        return person.toString();
    }
}
