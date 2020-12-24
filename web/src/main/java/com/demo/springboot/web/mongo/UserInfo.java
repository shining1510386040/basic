package com.demo.springboot.web.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 对应mongodb中的collection
 * @date 2020/12/23 18:33
 * @see
 */
@Document("userinfo")
public class UserInfo {

    @Id
    private String id;

    @Field("username")
    private String username;

    @Field("password")
    private String password;

    @Field("description")
    private String description;

    @Field("createTime")
    private Date createTime;

    @Field("bsflag")
    private String bsflag;

    @Field("updateTime")
    private Date updateTime;

    @Field("createBy")
    private String createBy;

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                ", bsflag='" + bsflag + '\'' +
                ", updateTime=" + updateTime +
                ", createBy='" + createBy + '\'' +
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getBsflag() {
        return bsflag;
    }

    public void setBsflag(String bsflag) {
        this.bsflag = bsflag;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
}
