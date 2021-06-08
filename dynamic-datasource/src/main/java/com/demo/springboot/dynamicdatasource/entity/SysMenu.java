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
 * 菜单信息表：自关联、树结构、冗余存储、gap级差
 * </p>
 *
 * @author Wenyi Cao
 * @since 2021-06-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysMenu extends Model<SysMenu> {

    private static final long serialVersionUID = 1L;

    /**
     * 逻辑主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 菜单名称
     */
    private String menuname;

    /**
     * 菜单id
     */
    private String menuid;

    /**
     * 上级菜单id
     */
    private String parentmenuid;

    /**
     * 当前的层级
     */
    private Integer currentlevel;

    /**
     * 级差
     */
    private Integer gap;

    private String createby;

    private LocalDate createdate;

    private String lastmodifyby;

    private LocalDate lastmodifydate;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
