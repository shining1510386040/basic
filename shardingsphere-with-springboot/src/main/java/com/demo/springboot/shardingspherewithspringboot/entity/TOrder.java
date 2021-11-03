package com.demo.springboot.shardingspherewithspringboot.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author Wenyi Cao
 * @since 2021-11-03
 */
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class TOrder extends Model<TOrder> {

    private static final long serialVersionUID = 1L;

    private String id;

    private Integer orderId;

    private String orderName;

    private Integer userId;

    private String orderDesc;

    private Integer bsflag;

    private Date createDate;

    private String createBy;

    private Date lastModifyDate;

    private String lastModifyBy;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
