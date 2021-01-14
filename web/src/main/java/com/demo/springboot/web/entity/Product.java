package com.demo.springboot.web.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 商品实体
 * @date 2021/1/13 18:38
 * @see
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private long id;
    /**
     * @ExcelProperty Excel列头，和属性对应关系
     */
    @ExcelProperty(value = "商品名称", index = 0)
    private String productName;

    @ExcelProperty(value = "商品描述", index = 1)
    private String productDesc;

    /**
     * 数字格式转化：@NumberFormat,保留2位小数
     */
    @ExcelProperty(value = "商品价格", index = 2)
    @NumberFormat(value = "#0.00")
    private BigDecimal productPrice;

    @ExcelProperty(value = "商品连接图片", index = 3)
    private String productImg;

    @ExcelProperty(value = "创建人", index = 4)
    private long createBy;

    @ExcelProperty(value = "创建日期", index = 5)
    @DateTimeFormat(value = "yyyy-MM-dd hh:mm:ss")
    private Date createDate;

    @ExcelProperty(value = "上次修改人", index = 7)
    private long lastmodifyBy;
    /**
     * 日期格式转换：DateTimeFormat
     * 忽略字段：ExcelIgnore
     */
    @ExcelProperty(value = "上次修改日期", index = 6)
    @DateTimeFormat(value = "yyyy-MM-dd hh:mm:ss")
    private Date lastmodifyDate;

    /**
     * 解析时忽略的字段：
     * 导入日期
     */
    @ExcelIgnore
    private Date syncDate;

}
