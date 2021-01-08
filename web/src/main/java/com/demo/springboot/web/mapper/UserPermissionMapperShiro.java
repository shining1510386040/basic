package com.demo.springboot.web.mapper;

import com.demo.springboot.web.entity.UserPermissionShiro;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 用户权限mapper
 * @date 2021/1/8 18:32
 * @see
 */
@Mapper
public interface UserPermissionMapperShiro {

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 根据用户名查询权限
     * @date 2021/1/8 19:23
     */
    UserPermissionShiro getByUsername(@Param("username") String username);

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 根据用户名，密码获取用户信息；
     * @date 2021/1/8 19:23
     */
    UserPermissionShiro getUserWithNameAndPass(@Param("username") String username, @Param("password") String password);
}
