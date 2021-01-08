package com.demo.springboot.web.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 用户权限
 * @date 2021/1/8 18:28
 * @see
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPermissionShiro {

    private Long id;
    private Long userId;
    private String userName;
    private String password;
    private String userPermission;

}
