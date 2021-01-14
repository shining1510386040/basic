package com.demo.springboot.web.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.demo.springboot.web.entity.Product;
import com.demo.springboot.web.mapper.ProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 读取Excel的监听器
 * @date 2021/1/13 19:12
 * @see
 */
public class ExcelListener extends AnalysisEventListener<Product> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    private ProductMapper productMapper;


    public ExcelListener(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    /**
     * 每5条数据入库一次，生产上可以设置为 2000条；
     * 入库后，清空内存list 的数据；防止oom
     */
    private final int BATCH_COUNT = 5;

    /**
     * 内存中的list
     */
    private List<Product> list = new ArrayList<>();

    /**
     * 一行一行的去读取Excel的内容，data封装的是一行数据，每读取一行本方法执行一次；
     */
    @Override
    public void invoke(Product data, AnalysisContext analysisContext) {

        logger.info("从Excel解析到一条数据：{}", JSON.toJSONString(data));
        // 导入日期
        data.setSyncDate(new Date());
        list.add(data);
        if (list.size() >= BATCH_COUNT) {
            // 1.入库
            productMapper.saveProductBatch(list);
            // 2.存储完成 清空list
            list.clear();
        }

    }

    /**
     * 解析sheet表头
     */
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {

        logger.info("解析到表头数据：{}", JSON.toJSONString(headMap));
        super.invokeHeadMap(headMap, context);
    }

    /**
     * 全部读取完成之后要做的事；
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 确保最后list内存中有遗留的数据，存储到db
        productMapper.saveProductBatch(list);
        logger.info("所有数据解析完成！");

    }
}
