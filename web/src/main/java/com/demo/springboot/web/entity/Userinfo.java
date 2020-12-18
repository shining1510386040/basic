package com.demo.springboot.web.entity;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 用户实体
 * @date 2020/12/18 15:36
 * @see
 */
public class Userinfo {

    private long id;
    private String userName;
    private String passWord;

    public Userinfo() {
    }

    public Userinfo(long id, String userName, String passWord, String realName) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.realName = realName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    private String realName;


}
