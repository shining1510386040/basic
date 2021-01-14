package com.demo.springboot.web.utils;

import com.alibaba.excel.EasyExcel;
import com.demo.springboot.web.entity.Product;
import com.demo.springboot.web.listener.ExcelListener;
import com.demo.springboot.web.mapper.ProductMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description Excel操作工具类
 * @date 2021/1/12 10:51
 * @see
 */
public class ExcelUtils {

    // todo ...use easyExcel ..


    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description java 的方式去读
     * @date 2021/1/13 19:57
     */
    public static void simpleRead() {
        // todo ...有问题

//        ApplicationContext context = new XmlWebApplicationContext();
//        ProductMapper productMapper = (ProductMapper) context.getBean("productMapper");
//        String filePath = "D:\\IdeaProjects\\basic\\web\\src\\main\\resources\\导入数据测试-20210113.xlsx";
//        EasyExcel.read(filePath, Product.class, new ExcelListener(productMapper)).sheet().doRead();

    }

    public static void main(String[] args) {
        simpleRead();
    }

}
