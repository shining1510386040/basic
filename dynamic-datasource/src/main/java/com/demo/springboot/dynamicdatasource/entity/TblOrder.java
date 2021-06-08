package com.demo.springboot.dynamicdatasource.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 这是MyBatis Generator自动生成的Model Class.
 * 对应的数据表是 : tbl_order
 * @author Lenovo
 * @date 2021-06-08 16:35:12
 */
public class TblOrder implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String ordername;

    /**
     * 
     */
    private String orderdesc;

    /**
     * 
     */
    private BigDecimal price;

    /**
     * 
     */
    private Date createdate;

    /**
     * 
     */
    private String createby;

    /**
     * 
     */
    private Date lastmodifydate;

    /**
     * 
     */
    private String lastmodifyby;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrdername() {
        return ordername;
    }

    public void setOrdername(String ordername) {
        this.ordername = ordername == null ? null : ordername.trim();
    }

    public String getOrderdesc() {
        return orderdesc;
    }

    public void setOrderdesc(String orderdesc) {
        this.orderdesc = orderdesc == null ? null : orderdesc.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby == null ? null : createby.trim();
    }

    public Date getLastmodifydate() {
        return lastmodifydate;
    }

    public void setLastmodifydate(Date lastmodifydate) {
        this.lastmodifydate = lastmodifydate;
    }

    public String getLastmodifyby() {
        return lastmodifyby;
    }

    public void setLastmodifyby(String lastmodifyby) {
        this.lastmodifyby = lastmodifyby == null ? null : lastmodifyby.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", ordername=").append(ordername);
        sb.append(", orderdesc=").append(orderdesc);
        sb.append(", price=").append(price);
        sb.append(", createdate=").append(createdate);
        sb.append(", createby=").append(createby);
        sb.append(", lastmodifydate=").append(lastmodifydate);
        sb.append(", lastmodifyby=").append(lastmodifyby);
        sb.append("]");
        return sb.toString();
    }
}