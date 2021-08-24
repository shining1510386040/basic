package com.demo.springboot.springbasic.dao;

import com.demo.springboot.springbasic.entity.TblSysUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 系统用户dao
 * @date 2021/8/24 16:40
 * @see
 */
public interface TblSysUserDao {

    /**
     * 保存
     */
    void saveUser(TblSysUser data);

    /**
     * 删除
     */
    void deleteUser(String id);

    /**
     * 修改
     */
    void updateUser(String id, TblSysUser data);

    /**
     * 列表查询
     */
    List<TblSysUser> listUser(Map<String, Object> condition);

    /**
     * 统计数量
     */
    int countUser(Map<String, Object> condition);
}
