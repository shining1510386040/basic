package com.demo.springboot.springbasic.dao.impl;

import com.demo.springboot.springbasic.dao.TblSysUserDao;
import com.demo.springboot.springbasic.entity.TblSysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 实现类
 * @date 2021/8/24 16:47
 * @see
 */
@Repository
public class TblSysUserDaoImpl implements TblSysUserDao {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void saveUser(TblSysUser data) {

        // todo ..
        String sql = "insert into tbl_sys_user(id,) values(?,?,?,?,?,?)";
//        Object[] params = new Object[6];
        Object[] params = {
                data.getId(),
                data.getUsername(),
                data.getLoginname()
        };
        int update = jdbcTemplate.update(sql, params);

    }

    @Override
    public void deleteUser(String id) {

    }

    @Override
    public void updateUser(String id, TblSysUser data) {


    }

    @Override
    public List<TblSysUser> listUser(Map<String, Object> condition) {
        // todo ...
        String sql = "select * from tbl_sys_user left join ...";
        Object[] params = new Object[5];
        List<TblSysUser> tblSysUserList = jdbcTemplate.queryForList(sql, TblSysUser.class, params);
        return tblSysUserList;
    }

    @Override
    public int countUser(Map<String, Object> condition) {
        return 0;
    }
}
