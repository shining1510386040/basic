package com.demo.springboot.springbasic.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 系统用户信息
 * @date 2021/8/24 16:37
 * @see
 */
@Data
@Builder
public class TblSysUser {

    private String id;

    private String username;

    private String loginname;

    private String password;

    private String email;

    private Integer isadmin;

    private Date createtime;

    private String createby;

    private Date lastmodifytime;

    private String lastmodifyby;


}
