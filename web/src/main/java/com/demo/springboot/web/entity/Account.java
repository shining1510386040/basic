package com.demo.springboot.web.entity;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 登录账户
 * @date 2020/12/28 12:31
 * @see
 */
public class Account {

    private String id;
    private String username;
    private String password;
    private String language;

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", language='" + language + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
