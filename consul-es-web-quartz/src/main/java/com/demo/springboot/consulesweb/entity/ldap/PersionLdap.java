package com.demo.springboot.consulesweb.entity.ldap;

import lombok.Data;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description ldap 实体，
 * @date 2021/2/3 16:28
 * @see
 */
@Data
@Entry(objectClasses = "inetOrgPerson",base = "ou=people,dc=didispace,dc=com")
public class PersionLdap {

    @Id
    private Name id;

    @DnAttribute(value = "uid", index = 3)
    private String uid;

    @Attribute(name = "cn")
    private String commonName;

    @Attribute(name = "sn")
    private String suerName;
    private String userPassword;
}
