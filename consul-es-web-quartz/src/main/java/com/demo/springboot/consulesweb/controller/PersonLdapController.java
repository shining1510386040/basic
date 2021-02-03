package com.demo.springboot.consulesweb.controller;

import com.demo.springboot.consulesweb.entity.jpa.ProductJpa;
import com.demo.springboot.consulesweb.entity.ldap.PersionLdap;
import com.demo.springboot.consulesweb.repository.jpa.ProductJpaRepository;
import com.demo.springboot.consulesweb.repository.ldap.PersonLdapRepository;
import com.demo.springboot.consulesweb.vo.PageResult;
import com.demo.springboot.consulesweb.vo.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.web.bind.annotation.*;

import javax.naming.Name;
import javax.naming.ldap.LdapName;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description ldap 仓储测试controller
 * @date 2021/1/26 19:22
 * @see
 */
@RestController
@RequestMapping("/ldap/person")
public class PersonLdapController {

    @Autowired
    private PersonLdapRepository personLdapRepository;

    @Autowired
    private LdapTemplate ldapTemplate;

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 查询单个
     * @date 2021/1/26 19:27
     */
    @GetMapping("/getOne/{id}")
    public PersionLdap getOne(@PathVariable long id) {
//        personLdapRepository.findById()

        return null;
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 保存
     * @date 2021/1/26 19:26
     */
    @PostMapping("/save")
    public PersionLdap save(PersionLdap data) {
        PersionLdap save = personLdapRepository.save(data);

        return save;
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 查询所有
     * @date 2021/1/26 19:28
     */
    @PostMapping("/list")
    public List<PersionLdap> findAll() {

        Iterable<PersionLdap> all = personLdapRepository.findAll();
        List ret = new ArrayList();
        all.forEach(item -> {
            ret.add(item);
        });
        return ret;
    }


    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 删除
     * @date 2021/1/29 11:07
     */
    @PostMapping("/delete")
    public void delete(@RequestParam long id) {
//        Name id1 = new LdapName(":" + id);
//        personLdapRepository.deleteById(id1);
//        ldapTemplate.delete();
    }


}
