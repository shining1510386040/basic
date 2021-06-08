package com.demo.springboot.dynamicdatasource.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Wenyi Cao
 * @since 2021-06-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysRoleMenu extends Model<SysRoleMenu> {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer roleid;

    private Integer menuid;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
