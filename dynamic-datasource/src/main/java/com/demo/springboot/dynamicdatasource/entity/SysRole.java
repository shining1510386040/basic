package com.demo.springboot.dynamicdatasource.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色信息表：数据冗余存储，自关联，树结构
 * </p>
 *
 * @author Wenyi Cao
 * @since 2021-06-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysRole extends Model<SysRole> {

    private static final long serialVersionUID = 1L;

    /**
     * 逻辑主键，记录id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色名称
     */
    private String rolename;

    /**
     * 角色id
     */
    private String roleid;

    /**
     * 上级角色id
     */
    private String parentroleid;

    /**
     * 当前层级
     */
    private Integer currentlevel;

    /**
     * 级差
     */
    private Integer gap;

    private LocalDate createdate;

    private String createby;

    private LocalDate lastmodifydate;

    private String lastmodifyby;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
