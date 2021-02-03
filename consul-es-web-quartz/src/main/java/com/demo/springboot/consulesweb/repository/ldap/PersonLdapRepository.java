package com.demo.springboot.consulesweb.repository.ldap;

import com.demo.springboot.consulesweb.entity.ldap.PersionLdap;
import org.springframework.data.repository.CrudRepository;

import javax.naming.Name;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description ldap 仓储服务
 * @date 2021/2/3 16:33
 * @see
 */
public interface PersonLdapRepository extends CrudRepository<PersionLdap, Name> {

}
