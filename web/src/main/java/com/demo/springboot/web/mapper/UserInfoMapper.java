package com.demo.springboot.web.mapper;

import com.demo.springboot.web.entity.Userinfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description mapper接口
 * @date 2020/12/18 15:39
 * @see
 */
@Mapper
public interface UserInfoMapper {


    int saveUser(Userinfo data);

    int updateUser(Userinfo data);

    int deleteUser(long id);

    Userinfo getOne(long id);

    List<Userinfo> getAll(Userinfo search);

    Userinfo getUserWithNameAndPass(String username, String password);
}
