package com.demo.springboot.dynamicdatasource.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户角色关系表
 * </p>
 *
 * @author Wenyi Cao
 * @since 2021-06-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserRole extends Model<SysUserRole> {

    private static final long serialVersionUID = 1L;

    /**
     * 逻辑id
     */
    private Long id;

    /**
     * 角色id
     */
    private String roleid;

    /**
     * 用户id
     */
    private String userid;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
